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
        final List<Sonnet> sonnets;
        List<Sonnet> collectedSonnets = new ArrayList<>();
        //        try(var sonnetStream = URI.create(url)
        //            .toURL()
        //            .openStream(); var reader = new BufferedReader(new InputStreamReader(
        //                sonnetStream)); var lines = reader.lines())

        try(var inputStream = this.getClass()
            .getResourceAsStream("/pg1041.txt"); var reader = new BufferedReader(
            new InputStreamReader(inputStream)); var lines = reader
            .lines())
        {
            collectedSonnets.addAll(lines.skip(SONNET_START)
                .gather(gatherSonnets()).peek(s -> s.lines().forEach(IO::println))
                .toList());

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
        sonnets = collectedSonnets;
        IO.println("# sonnets = %d".formatted(sonnets.size()));
    }

    Gatherer<String, ?, Sonnet> gatherSonnets()
    {
        class SonnetLines
        {

            int blankLines = 0;
            final Sonnet sonnet = new Sonnet();
        }
        Supplier<SonnetLines> newSonnet = SonnetLines::new;
        Gatherer.Integrator<SonnetLines, String, Sonnet> getSonnet =
            (sonnetLines, line, downstream) ->
        {

            if(downstream.isRejecting()
                || line.startsWith("*** END OF THE PROJECT GUTENBERG EBOOK"))
                return false;

            final Sonnet sonnet = sonnetLines.sonnet;

            if(!line.isBlank())
            {
                if(sonnetLines.blankLines % 2 != 0) sonnet.add(line);
                else ++sonnetLines.blankLines;
            }
            else if(!sonnet.lines().isEmpty())
            {
                ++sonnetLines.blankLines;
                var isRejecting = downstream.push(sonnet);
                sonnet.clear();
                return isRejecting;
            }

            return true;
        };
        return Gatherer.ofSequential(newSonnet, getSonnet);
    }
}
