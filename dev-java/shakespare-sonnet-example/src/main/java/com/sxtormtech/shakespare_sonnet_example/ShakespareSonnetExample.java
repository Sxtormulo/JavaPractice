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
public class ShakespareSonnetExample
{

    private static final int ERROR = -1;
    private static final int SONNET_START = 33;

    void main()
    {
        final String url = "https://www.gutenberg.org/cache/epub/1041/pg1041.txt";
        final var sonnets = new ArrayList<Sonnet>();
        try(var sonnetStream = URI.create(url)
            .toURL()
            .openStream(); var reader = new SonnetReader(sonnetStream))
        {
            reader.skipLines(SONNET_START);
            var sonnet = reader.readNextSonnet();
            while(sonnet != null)
            {
                sonnets.add(sonnet);
                sonnet = reader.readNextSonnet();
            }

        }
        catch(MalformedURLException malformedURL)
        {
            System.err.println("Malformed URL %s".formatted(malformedURL.getMessage()));
            System.exit(ERROR);
        }
        catch(IOException iOException)
        {
            iOException.printStackTrace();
            System.exit(ERROR);
        }
        IO.println("# sonnets = %d".formatted(sonnets.size()));
    }
}
