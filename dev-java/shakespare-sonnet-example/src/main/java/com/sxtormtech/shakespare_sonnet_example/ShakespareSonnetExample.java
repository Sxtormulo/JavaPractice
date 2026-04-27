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
import java.lang.System.Logger.Level;

/**
 *
 * @author Sxtormulo
 */
public class ShakespareSonnetExample
{

    private static final int ERROR = -1;
    private static final int SONNET_START = 33;

    /**
     * Collect the sonnets from the given resource. First try to use a file saved with
     * the sonnets name. If the file if not found then try to get it from the web.
     *
     * @param sonnetsFileName   the sonnets file name
     * @param sonnetsWebAddress the sonnets web url
     * @return the list of sonnets in the resourse
     */
    private List<Sonnet> collectSonnets(String sonnetsFileName,
                                        String sonnetsWebAddress)
    {
        List<Sonnet> collectedSonnets = new ArrayList<>();
        var sonnetURL = getClass().getResource(sonnetsFileName);
        if(sonnetURL == null)
        {
            try
            {
                sonnetURL = URI.create(sonnetsWebAddress).toURL();
            }
            catch(MalformedURLException malformedURLException)
            {
                System.getLogger(ShakespareSonnetExample.class.getName())
                    .log(Level.ERROR, "Entered malformed url: "
                         .formatted(malformedURLException.getMessage()),
                         malformedURLException);
            }
        }

        try(final var inputStream = sonnetURL.openStream();
            final var reader = new BufferedReader(new InputStreamReader(inputStream));
            final var lines = reader.lines())
        {
            collectedSonnets.addAll(lines.skip(SONNET_START)
                .gather(gatherSonnets())
                .toList());

        }
        catch(IOException iOException)
        {
            logErrorAndExit("Error while proccessing the sonnets", iOException);
        }
        return collectedSonnets;

    }

    /**
     * Compress the passed sonnets. And save it to a binary file. Saving in the binary
     * file the necessary data for decompression
     *
     * @param compressedSonnetsPath the path where the compressed sonnets will be saved
     * @param sonnets               the sonnets to compress
     */
    private void compressSonnetsToBinFile(Path compressedSonnetsPath,
                                          final List<Sonnet> sonnets)
    {
        int numberOfSonnets = sonnets.size();
        try(var sonnetFile = Files.newOutputStream(compressedSonnetsPath);
            var sonnetDataWriter = new DataOutputStream(sonnetFile))
        {
            List<SonnetData> compressedSonnetsData = new ArrayList<>();
            byte[] encodedSonnetsBytes = null;

            try(var encodedSonnets = new ByteArrayOutputStream())
            {
                for(var sonnet : sonnets)
                {
                    final var compressedSonnet = sonnet.getCompressedBytes();
                    compressedSonnetsData.add(new SonnetData(encodedSonnets.size(),
                                                             compressedSonnet.length));
                    encodedSonnets.write(compressedSonnet);
                }
                sonnetDataWriter.writeInt(numberOfSonnets);
                for(var sonnetData : compressedSonnetsData)
                {
                    sonnetDataWriter.writeInt(sonnetData.offset());
                    sonnetDataWriter.writeInt(sonnetData.length());
                }
                encodedSonnetsBytes = encodedSonnets.toByteArray();

            }
            sonnetFile.write(encodedSonnetsBytes);
        }
        catch(IOException iOException)
        {
            logErrorAndExit("Error while encoding Sonnets", iOException);
        }
    }

    /**
     * Create a temporary file to save the compressed sonnets
     *
     * @return the path of the created file
     */
    private Path createTmpSonnetBinFile()
    {
        Path compressedSonnetsPath = null;
        try
        {
            final Path home = Path.of(System.getProperty("user.home"), "/tmp");
            compressedSonnetsPath = Files.createTempFile(home, "sonnets",
                                                         ".bin");
        }
        catch(IOException iOException)
        {
            logErrorAndExit("Error at creating tempfile", iOException);
        }
        return compressedSonnetsPath;
    }

    /**
     * Gather the sonnets in a stream. Skipping the header of each sonnet and returnting
     * the lines in it
     *
     * @return the lines that conforms the sonnet
     */
    private Gatherer<String, ?, Sonnet> gatherSonnets()
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

    /**
     * Log the error message and exit the application
     *
     * @param errorMessage the message to log
     * @param iOException  the error to log
     */
    private void logErrorAndExit(String errorMessage, IOException iOException)
    {
        System.getLogger(ShakespareSonnetExample.class.getName())
            .log(Level.ERROR, errorMessage, iOException);
        System.exit(ERROR);
    }

    void main()
    {
        final String sonnetWebAddress =
            "https://www.gutenberg.org/cache/epub/1041/pg1041.txt";
        final List<Sonnet> sonnets;
        int numberOfSonnets;

        final String sonnetsFilename = "/pg1041.txt";

        sonnets = collectSonnets(sonnetsFilename, sonnetWebAddress);
        numberOfSonnets = sonnets.size();
        Path compressedSonnetsPath = createTmpSonnetBinFile();

        compressSonnetsToBinFile(compressedSonnetsPath, sonnets);

        IO.println("# sonnets = %d".formatted(sonnets.size()));
    }

    /**
     * Local record to save sonnet data
     *
     * @param offset the offset int the file
     * @param length the length of the sonnet
     */
    private record SonnetData(int offset, int length)
        {

    }

}
