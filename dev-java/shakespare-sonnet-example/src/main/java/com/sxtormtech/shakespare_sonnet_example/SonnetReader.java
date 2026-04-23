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
 *
 * @author Sxtormulo
 */
public class SonnetReader extends BufferedReader
{

    public SonnetReader(Reader reader)
    {
        super(reader);
    }

    public SonnetReader(InputStream inputStream)
    {
        this(new InputStreamReader(inputStream));
    }

    public void skipLines(int lines) throws IOException
    {
        for(int i = 0; i < lines; i++)
        {
            readLine();
        }
    }

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

    private String skipBlankLines() throws IOException
    {
        String line;
        do
        {
            line = readLine();
        } while(line.isBlank());
        return line;
    }

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
