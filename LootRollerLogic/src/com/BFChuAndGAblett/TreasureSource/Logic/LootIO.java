/**
 * 
 */
package com.BFChuAndGAblett.TreasureSource.Logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Brian Chu and Garrick Ablett
 * 
 *         Handles basic file IO, has no special functions related to
 *         recognizing item/loot information
 * 
 */
public class LootIO {

    private String fileName;
    private BufferedReader in;

    public LootIO() {
        super();
        this.fileName = "";
        this.in = null;
    }

    /**
     * @param fileName
     * @param in
     */
    public LootIO(String fileName, BufferedReader in) throws IOException {
        super();
        this.fileName = fileName;
        try {
            this.in = new BufferedReader(new FileReader(new File(fileName)));
        } catch (IOException e) {
            System.out.println("e: " + e);
        }
    }

    public void printLootIO() throws IOException {
        while (this.in.ready()) {
            System.out.println(this.in.readLine());
        }
    }

    public void close() throws IOException {
        if (this.getIn() != null) {
            try {
                this.in.close();
            } catch (IOException e) {
                System.out.println("e: " + e);
            }
        }
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     *            the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the in
     */
    public BufferedReader getIn() {
        return in;
    }

    /**
     * @param in
     *            the in to set
     */
    public void setIn(BufferedReader in) {
        this.in = in;
    }

}
