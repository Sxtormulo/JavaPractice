/*
 * Copyright (C) 2026 Sxtormulo
 * SPDX-License-Identifier: GPL-3.0-or-later
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.sxtormtech.shakespare_sonnet_example;

import module java.base;

/**
 * Reads sonnets from an input stream. Buffering sonnets. Each read returns a new sonnet
 */
public class SonnetReader extends BufferedReader
{

    /**
     * Creates a buffering sonnet input stream, that uses the default size buffer.
     *
     * @param reader the sonnet stream reader
     */
    public SonnetReader(Reader reader)
    {
        super(reader);
    }

    /**
     * Creates a buffering sonnet input stream from a input stream, that uses the
     * default size buffer.
     *
     * @param inputStream the sonnet input stream
     */
    public SonnetReader(InputStream inputStream)
    {
        this(new InputStreamReader(inputStream));
    }

    /**
     * Skip the indicated number of lines
     *
     * @param lines the lines to skip
     * @throws IOException If an I/O error occurs
     */
    public void skipLines(int lines) throws IOException
    {
        for(int i = 0; i < lines; i++)
        {
            readLine();
        }
    }

    /**
     * Skip the header of the next sonnet
     *
     * @return the next line after the header
     * @throws IOException If an I/O error occurs
     */
    private String skipSonnetHeader() throws IOException
    {
        String line = skipBlankLines();

        if(line.startsWith("*** END OF THE PROJECT GUTENBERG EBOOK"))
        {
            return null;
        }

        line = skipBlankLines();
        return line;
    }

    /**
     * Skips blank lines until the next line with content
     *
     * @return the line with content after the blank lines
     * @throws IOException If I/O error occurs
     */
    private String skipBlankLines() throws IOException
    {
        String line;
        do
        {
            line = readLine();
        } while(line.isBlank());
        return line;
    }

    /**
     * Reads the next sonnet
     *
     * @return the next sonnet in the stream
     * @throws IOException If an I/O error occurs
     */
    public Sonnet readNextSonnet() throws IOException
    {
        String line = skipSonnetHeader();
        if(line == null)
        {
            return null;
        }
        else
        {
            var sonnet = new Sonnet();
            while(!line.isBlank())
            {
                sonnet.add(line);
                line = readLine();
            }
            return sonnet;
        }
    }

}
