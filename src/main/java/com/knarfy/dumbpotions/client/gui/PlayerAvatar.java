package com.knarfy.dumbpotions.client.gui;

import com.knarfy.dumbpotions.DumbPotions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PlayerAvatar {
    public static String getUrl(String playerName) {
        return "https://render.skinmc.net/3d.php?user=" + playerName + "&vr=-10&hr0&hrh=25&aa=&headOnly=true&ratio=50";
    }

    public static byte[] getBytes(String url) {
        var out = new ByteArrayOutputStream();
        InputStream in = null;

        try {
            in = new URL(url).openStream();
            var chunk = new byte[4096];
            int n;

            while ((n = in.read(chunk)) > 0) {
                out.write(chunk, 0, n);
            }
        } catch (IOException ex) {
            DumbPotions.LOGGER.error("Cannot read bytes from player avatar URL: " + ex.getMessage());

            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    DumbPotions.LOGGER.error("Cannot close InputStream: " + ex.getMessage());
                }
            }
        }

        return out.toByteArray();
    }
}
