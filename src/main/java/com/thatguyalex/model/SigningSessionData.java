/**
 * DigiDoc4j Hwcrypto Demo
 *
 * The MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.thatguyalex.model;

import org.digidoc4j.Container;
import org.digidoc4j.DataToSign;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.Serializable;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class SigningSessionData implements Serializable {

    private Container container;
    private DataToSign dataToSign;
    private String name;
    private String isik;

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }

    public void setDataToSign(DataToSign dataToSign) {
        this.dataToSign = dataToSign;
    }

    public DataToSign getDataToSign() {
        return dataToSign;
    }

    public String getName() {
        return name;
    }

    public String getIsik() {
        return isik;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsik(String isik) {
        this.isik = isik;
    }

    public void setNameFromDN(String name) { //TODO: catch exceptions
        int start = name.indexOf("CN=\"");
        String[] nameparse = name.substring(start+4, name.indexOf('"', start+5)).split(",");
        this.name = String.join(" ", nameparse[1], nameparse[0]);
        this.isik = nameparse[2];
    }

    public String getFilePath(boolean signed) {
        if (signed) {
            return "src/test/resources/signed/" + getIsik() + ".txt";
        }
        return "src/test/resources/texts/" + getIsik() + ".bdoc";
    }

    public String getFileName(boolean signed) {
        if (signed) {
            return getIsik() + ".bdoc";
        }
        return getIsik() + ".txt";
    }

    public boolean doesFileExist() {
        File f = new File(getFilePath(true));
        return (f.exists() && !f.isDirectory());
    }
}
