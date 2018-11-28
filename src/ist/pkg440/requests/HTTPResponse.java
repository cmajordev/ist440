/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ist.pkg440.requests;

import java.io.IOException;

/**
 *
 * @author chrismorris
 */
public interface HTTPResponse<T, U> {
    void invalidRequestError(InvalidRequestError error);
    void responseError(U error);
    void ioError(IOException error);
    void success(T response);
}
