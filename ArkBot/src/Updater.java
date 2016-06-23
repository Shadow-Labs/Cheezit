import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

//http://www.dreamincode.net/forums/topic/190944-creating-an-updater-in-java/
public class Updater {
	public static String webVersion = "";
	private static String versionURL = "https://raw.githubusercontent.com/Shadow-Labs/Cheezit/master/ArkBot/ArkBotFiles/Version/CurrentVersion.txt";
	private static String jarURL = "";
	private static String zipURL = "https://github.com/Shadow-Labs/Cheezit/archive/master.zip";
	private String root = "update/";
	
	public Updater() {
		try {
			webVersion = getLatestVersion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jarURL = "https://github.com/Shadow-Labs/Cheezit/blob/master/ArkBot/ArkBot" + webVersion + ".jar?raw=true";
		zipURL = "https://github.com/Shadow-Labs/Cheezit/archive/master.zip";
	}
	
	public static String getLatestVersion() throws Exception{
		String data = getData(versionURL);
		webVersion = data.substring(0,6);
		return data.substring(0, 6);
	}
	
	public static String getData(String address) {
		try {
			URL url = new URL(address);
			
			InputStream html = null;
			html = url.openStream();
			
			int c = 0;
			StringBuffer buffer = new StringBuffer("");
			while (c != -1) {
				c = html.read();
				buffer.append((char) c);
			}
			return buffer.toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public void DownloadZip() {
		UpdateWindow(zipURL);
	}
	
	private void UpdateWindow(String downloadURL) {
		Image img = new ImageIcon("ArkBotFiles/Images/ArkBotLogo.png").getImage();
		ImageIcon icon = new ImageIcon(img.getScaledInstance(100, 100, 0));
        ImageIcon icon2 = new ImageIcon(new ImageIcon("ArkBotFiles/Images/ShadowLabsSmall.png").getImage().getScaledInstance(100, 100, 0));
		
        JPanel bgPanel = new BgPanel(new ImageIcon("ArkBotFiles/Images/LoadingScreenBackground.png").getImage());
        bgPanel.setLayout(new BorderLayout());
        
        JFrame lScreen = new JFrame("");
        if (downloadURL == zipURL) {
        	lScreen = new JFrame("Downloading ArkBot" + webVersion);
        } else if (downloadURL == jarURL) {
        	lScreen = new JFrame("Updating to ArkBot" + webVersion);
        }
		lScreen.setIconImage(img);
        lScreen.setSize(360,200);
        lScreen.setResizable(false);
        lScreen.setLocationRelativeTo(null);
        lScreen.setLayout(new GridLayout());
        
        JPanel barPanel = new JPanel();
        barPanel.setOpaque(false);
        JProgressBar bar = new JProgressBar();
        bar.setOpaque(false);
        bar.setForeground(Color.DARK_GRAY);
        int MIN = 0;
        int MAX = 100;
        bar.setMinimum(MIN);
        bar.setMaximum(MAX);
        bar.setStringPainted(true);
        
        barPanel.add(bar);
        bar.setPreferredSize(new Dimension (300,32));
        
        JLabel image1 = new JLabel("", icon, JLabel.CENTER);
        JLabel image2 = new JLabel("", icon2, JLabel.CENTER);
        JPanel imgPanel = new JPanel(new FlowLayout());
        //imgPanel.setOpaque(false);
        imgPanel.add(image2);
        imgPanel.add(image1);
        
        bgPanel.add(imgPanel, BorderLayout.NORTH);
        bgPanel.add(barPanel, BorderLayout.CENTER);
        lScreen.add(bgPanel);
        lScreen.setContentPane(bgPanel);
        lScreen.setVisible(true);
        
        // Download File
        int progress = MIN;
        int load = 0;
		try {
			URL url = new URL(downloadURL);
	        URLConnection conn = url.openConnection();
	        InputStream is = conn.getInputStream();
	        long max = conn.getContentLength();
	        bar.setMaximum((int) max);
	        BufferedOutputStream fOut = null;
	        if (downloadURL == zipURL) {
	        	fOut = new BufferedOutputStream(new FileOutputStream(new File("ArkBot" + webVersion + ".zip")));
	        } else if (downloadURL == jarURL) {
	        	fOut = new BufferedOutputStream(new FileOutputStream(new File("ArkBot" + webVersion + ".Jar")));
	        }
	        byte[] buffer = new byte[32 * 1024];
	        int bytesRead = 0;
	        int in = 0;
	        while ((bytesRead = is.read(buffer)) != -1) {
	        	bar.setString("Downloading " + in + " of " + max);
	        	bar.setValue(in);
	        	System.out.println(bytesRead);
	            in += bytesRead;
	            fOut.write(buffer, 0, bytesRead);
	        }
	        fOut.flush();
	        fOut.close();
	        is.close();
	        ArkBotGUI.GUIText("Download Complete");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
        try {
			Thread.sleep(250);
			lScreen.dispose();
	        Thread.sleep(750);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean UpdatePrompt() {
			JPanel msgPanel = new JPanel();
			msgPanel.setLayout(new BorderLayout());
			JLabel requirements = new JLabel("<html>ArkBot has detected a newer version.<br><br>"
							+ "Current Version: " + ArkBot.version + "<br>"
				    		+ "Updated Version: " + webVersion + "<br><br>"
				    		+ "Would you like to update?<br></html>");
			msgPanel.add(requirements, BorderLayout.NORTH);
			int value = (JOptionPane.showConfirmDialog(ArkBotGUI.GUI, msgPanel,"Update Avaliable", JOptionPane.YES_NO_OPTION));
			if (value == 0) {
				return true;
			}
			return false;
	}
	
    public void download() throws HeadlessException, Exception
    {
    	if (newVersionExists() && UpdatePrompt()) {
	        try {
	        	UpdateWindow(jarURL);
	        	//ArkBotGUI.GUIText("Updating ArkBot from" + ArkBot.version + " to " + webVersion);
	            //downloadFile("https://github.com/Shadow-Labs/ArkBot/raw/master/ArkBot/ArkBot" + webVersion + ".jar");
//	            unzip();
//	            copyFiles(new File(root),new File("").getAbsolutePath());
//	            cleanup();
	        	
	            ArkBotGUI.GUIText("Update Finished.");
	            ArkBotGUI.GUIText("Lauching ArkBot" + webVersion + " in 3...");
	            ArkBot.bot.delay(1000);
	            ArkBotGUI.GUIText("Lauching ArkBot" + webVersion + " in 2...");
	            ArkBot.bot.delay(1000);
	            ArkBotGUI.GUIText("Lauching ArkBot" + webVersion + " in 1...");
	            ArkBot.bot.delay(1000);
	            launch();
	        } catch (Exception ex) {
	        	ArkBotGUI.GUIText("JAVA ERROR: " + ex.getMessage());
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "An error occured while preforming update!");
	        }
    	}
    }
    private void launch() throws FileNotFoundException, UnsupportedEncodingException
    {
        String[] run = {"java","-jar","ArkBot" + webVersion + ".jar"};
        try {
            Runtime.getRuntime().exec(run);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Update Version
        ArkBotGUI.GUIText("Updated CurrentVersion.txt");
        PrintWriter writer = new PrintWriter ("ArkBotFiles\\Version\\CurrentVersion.txt", "UTF-8");
        writer.print(webVersion);
        writer.close();
        
        // Reset Settings
        ArkBotSettings.ResetSettings();
        
        ArkBot.log.CloseLog();
        System.exit(0);
    }
//    private void cleanup()
//    {
//    	ArkBotGUI.GUIText("Performing Cleanup");
//        File f = new File("update.zip");
//        f.delete();
//        remove(new File(root));
//        new File(root).delete();
//    }
//    private void remove(File f)
//    {
//        File[]files = f.listFiles();
//        for(File ff:files)
//        {
//            if(ff.isDirectory())
//            {
//                remove(ff);
//                ff.delete();
//            }
//            else
//            {
//                ff.delete();
//            }
//        }
//    }
//    private void copyFiles(File f,String dir) throws IOException
//    {
//        File[]files = f.listFiles();
//        for(File ff:files)
//        {
//            if(ff.isDirectory()){
//                new File(dir+"/"+ff.getName()).mkdir();
//                copyFiles(ff,dir+"/"+ff.getName());
//            }
//            else
//            {
//                copy(ff.getAbsolutePath(),dir+"/"+ff.getName());
//            }
//
//        }
//    }
//    public void copy(String srFile, String dtFile) throws FileNotFoundException, IOException{
//
//          File f1 = new File(srFile);
//          File f2 = new File(dtFile);
//
//          InputStream in = new FileInputStream(f1);
//
//          OutputStream out = new FileOutputStream(f2);
//
//          byte[] buf = new byte[1024];
//          int len;
//          while ((len = in.read(buf)) > 0){
//            out.write(buf, 0, len);
//          }
//          in.close();
//          out.close();
//      }
//    private void unzip() throws IOException
//    {
//         int BUFFER = 2048;
//         BufferedOutputStream dest = null;
//         BufferedInputStream is = null;
//         ZipEntry entry;
//         ZipFile zipfile = new ZipFile("update.zip");
//         Enumeration e = zipfile.entries();
//         (new File(root)).mkdir();
//         while(e.hasMoreElements()) {
//            entry = (ZipEntry) e.nextElement();
//            ArkBotGUI.GUIText("Extracting " + entry);
//            if(entry.isDirectory())
//                (new File(root+entry.getName())).mkdir();
//            else{
//                (new File(root+entry.getName())).createNewFile();
//                is = new BufferedInputStream
//                  (zipfile.getInputStream(entry));
//                int count;
//                byte data[] = new byte[BUFFER];
//                FileOutputStream fos = new
//                  FileOutputStream(root+entry.getName());
//                dest = new
//                  BufferedOutputStream(fos, BUFFER);
//                while ((count = is.read(data, 0, BUFFER))
//                  != -1) {
//                   dest.write(data, 0, count);
//                }
//                dest.flush();
//                dest.close();
//                is.close();
//            }
//         }
//
//    }
//    private void downloadFile(String link) throws MalformedURLException, IOException
//    {
//        URL url = new URL(link);
//        URLConnection conn = url.openConnection();
//        InputStream is = conn.getInputStream();
//        long max = conn.getContentLength();
//        ArkBotGUI.GUIText("Downloading file. Update Size: " + max + "Bytes");
//        BufferedOutputStream fOut = new BufferedOutputStream(new FileOutputStream(new File("ArkBot" + webVersion + ".Jar")));
//        byte[] buffer = new byte[32 * 1024];
//        int bytesRead = 0;
//        int in = 0;
//        while ((bytesRead = is.read(buffer)) != -1) {
//            in += bytesRead;
//            fOut.write(buffer, 0, bytesRead);
//        }
//        fOut.flush();
//        fOut.close();
//        is.close();
//        ArkBotGUI.GUIText("Download Complete");
//
//    }
    private boolean newVersionExists() throws Exception {
    	boolean version = false;
    	if (!ArkBot.version.equals(getLatestVersion())) {
    		version = true;
    	}
    	
    	return version;
    }
}
