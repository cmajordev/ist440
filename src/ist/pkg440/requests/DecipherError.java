/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440.requests;

import java.util.ArrayList;

/**
 *
 * @author chrismorris
 */
public class DecipherError {
    
    private ArrayList<BruteForceProcess> failingProcesses;
    private ArrayList<BruteForceProcess> successfulProcesses;
    private String message;
    
    /**
     * @return the failingProcesses
     */
    public ArrayList<BruteForceProcess> getFailingProcesses() {
        return failingProcesses;
    }

    /**
     * @param failingProcesses the failingProcesses to set
     */
    public void setFailingProcesses(ArrayList<BruteForceProcess> failingProcesses) {
        this.failingProcesses = failingProcesses;
    }

    /**
     * @return the successfulProcesses
     */
    public ArrayList<BruteForceProcess> getSuccessfulProcesses() {
        return successfulProcesses;
    }

    /**
     * @param successfulProcesses the successfulProcesses to set
     */
    public void setSuccessfulProcesses(ArrayList<BruteForceProcess> successfulProcesses) {
        this.successfulProcesses = successfulProcesses;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
