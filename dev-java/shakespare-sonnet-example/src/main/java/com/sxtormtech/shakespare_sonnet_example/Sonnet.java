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
 * Record class to contain a Sonnet.
 *
 * @param lines the lines that constitute a sonnet
 */
record Sonnet(List<String> lines)
    {

    /**
     * Create a {@code Sonnet} of the lines
     *
     * @param lines the lines that make up the Sonnet
     */
    public Sonnet
    {
        lines = new ArrayList<>(lines);
    }

    /**
     * Create an empty {@code Sonnet}
     */
    public Sonnet()
    {
        this(new ArrayList<>());
    }

    /**
     * Return a new {@code Sonnet} with a immutable copy of the lines of the previous
     * sonnet
     *
     * @param original the {@code Sonnet} to be copied
     * @return a new sonnet with the original datam
     */
    public static Sonnet of(Sonnet original)
    {
        return new Sonnet(original.lines());
    }

    /**
     * Add a line to the {@code Sonnet}
     *
     * @param line the new line of the sonnet
     */
    /**
     * Add a line to the {@code Sonnet}
     *
     * @param line the new line of the sonnet
     */
    public void add(String line)
    {
        lines.add(line);
    }

    /**
     * Remove all lines from the Sonnet
     */
    public void clear()
    {
        lines.clear();
    }

    /**
     * Returns the lines that constitute the {@code Sonnet}. <strong>The returned lines
     * are immutable.</strong>
     *
     * @return the lines of the sonnet
     */
    @Override
    public List<String> lines()
    {
        return List.copyOf(lines);
    }

    /**
     * Returns a compressed representation of the lines in the sonnet.
     *
     * @return the compressed bytes that represent the sonnet.
     * @throws IOException If an I/O error occurs
     */
    byte[] getCompressedBytes() throws IOException
    {
        final var byteArrayOutputStream = new ByteArrayOutputStream();
        try(var printer = new PrintWriter(new GZIPOutputStream(byteArrayOutputStream)))
        {
            lines.forEach(printer::println);
        }
        return byteArrayOutputStream.toByteArray();
    }

}
