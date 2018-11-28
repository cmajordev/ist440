/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440.requests;

/**
 *
 * @author chrismorris
 */
public interface DecipherImageProccessDelegate {
    void finishedStep(DecipherImageProccessStep step);
    void success(String decipheredMessage);
    void error(String errorMessage);
}
