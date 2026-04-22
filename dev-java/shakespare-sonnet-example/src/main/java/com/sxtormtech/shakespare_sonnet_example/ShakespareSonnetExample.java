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

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import module java.base;

/**
 *
 * @author Sxtormulo
 */
public class ShakespareSonnetExample
{

    void main()
    {
        final String url = "https://www.gutenberg.org/cache/epub/1041/pg1041.txt";
        var sonnetsURI = URI.create(url);
        HttpResponse<InputStream> sonnetsResponse = null;
        try
        {
            final HttpRequest sonnetsRequest =
                HttpRequest.newBuilder(sonnetsURI)
                    .GET()
                    .build();
            sonnetsResponse =
                CLIENT.send(sonnetsRequest, HttpResponse.BodyHandlers.ofInputStream());

        }
        catch(IOException | InterruptedException iOException)
        {
            System.err.println("Exception when retrieving the url" + iOException
                .getMessage());
            System.exit(-1);
        }
//        try(final var sonnetsStream = sonnetsResponse.body();  )
//        {
//
//        }

    }
    private static final HttpClient CLIENT = HttpClient.newHttpClient();

}
