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
 * Record class to contain the a Sonnet.
 *
 * @param lines the lines that constitute a sonnet
 */
public record Sonnet(List<String> lines)
    {

    /**
     * Create a {@code Sonnet} of the lines
     *
     * @param lines the lines that make up the Sonnet
     */
    public Sonnet(List<String> lines)
    {
        this.lines = new ArrayList<>(lines);
    }

    /**
     * Create an empty {@code Sonnet}
     */
    public Sonnet()
    {
        this(new ArrayList<>());
    }

    /**
     * Returns the lines that constitute the {@code Sonnet}
     *
     * @return the lines of the sonnet
     */
    @Override
    public List<String> lines()
    {
        return List.copyOf(lines);
    }

    /**
     * Add a line to the {@code Sonnet}
     *
     * @param line the new line of the sonnet
     */
    public void add(String line)
    {
        lines.add(line);
    }

}
