package com.epam.pages.actions;

import com.epam.setup.systemsettings.ImportApplicationVariables;
import com.jcraft.jsch.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class GCMSWinScpServerConnectionPage {

    public static ChannelSftp setup(String serverUrl) throws JSchException, IOException {

        Session jschSession = null;
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        JSch jsch = new JSch();
        ImportApplicationVariables.setVariables();
        jsch.setKnownHosts(ImportApplicationVariables.hostFileName);
        System.out.println("Server url=" + serverUrl);
        InetAddress inetAddress = InetAddress.getByName(serverUrl);
        String raw = inetAddress.getHostAddress();
        System.out.println(raw);
        jschSession = jsch.getSession(ImportApplicationVariables.serverUsername, raw, 22);
        jschSession.setConfig(config);
        jschSession.setPassword(ImportApplicationVariables.serverPassword);
        jschSession.connect();
        return (ChannelSftp) jschSession.openChannel("sftp");

    }

    public static void putFile(ChannelSftp setup, String fileName, String filePath) throws JSchException, SftpException, UnknownHostException {
        ChannelSftp channelSftp = setup;
        channelSftp.connect();
        Session session = channelSftp.getSession();
        System.err.println("Is session connected= " + session.isConnected());
        String localFile = "src//test//resources//reference//" + fileName;
        String remoteDirectory = filePath + "//" + fileName;
        //channelSftp.rm();
        System.out.println(localFile);
        System.out.println(remoteDirectory);

        channelSftp.put(localFile, remoteDirectory);
        System.err.println("File has been kept");
        channelSftp.exit();
        channelSftp.disconnect();
        session.disconnect();
        System.err.println(channelSftp.isConnected());
    }

    public static void deleteFile(String serverUrl, String fileName, String filePath) throws JSchException, SftpException, IOException {
        ChannelSftp channelSftp = setup(serverUrl);
        channelSftp.connect();
        Session session = channelSftp.getSession();
        System.err.println("Is session connected= " + session.isConnected());
        String remoteDirectory = filePath + "//" + fileName;
        channelSftp.rm(remoteDirectory);
        System.out.println(remoteDirectory);
        System.err.println("File has been deleted");
        channelSftp.exit();
        channelSftp.disconnect();
        session.disconnect();
        System.err.println(channelSftp.isConnected());
    }

    public static boolean findFile(String serverUrl, String fileName, String filePath) throws JSchException, SftpException, IOException {
        ChannelSftp channelSftp = setup(serverUrl);
        channelSftp.connect();
        Session session = channelSftp.getSession();
        System.err.println("Is session connected= " + session.isConnected());
        boolean flag = false;

        String remoteDirectory = filePath + "/" + fileName;
        System.out.println(remoteDirectory);
        try {
            channelSftp.lstat(remoteDirectory);
            flag = true;
            System.err.println("File exits");
        }
        catch (SftpException e) {
            if (e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE) {
                flag = false;
                System.err.println("File does not exits");
            } else {
                throw e;
            }
        }
        channelSftp.exit();
        channelSftp.disconnect();
        session.disconnect();
        System.err.println("Sft connected:" + channelSftp.isConnected());
        return flag;
    }

}



