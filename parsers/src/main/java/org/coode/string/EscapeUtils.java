/*
 * This file is part of the OWL API.
 *
 * The contents of this file are subject to the LGPL License, Version 3.0.
 *
 * Copyright (C) 2011, The University of Manchester
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
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 *
 *
 * Alternatively, the contents of this file may be used under the terms of the Apache License, Version 2.0
 * in which case, the provisions of the Apache License Version 2.0 are applicable instead of those above.
 *
 * Copyright 2011, University of Manchester
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.coode.string;

/** Author: Matthew Horridge<br>
 * The University Of Manchester<br>
 * Bio-Health Informatics Group<br>
 * Date: 16-Apr-2008<br>
 * <br>
 * 
 * @deprecated use EscapeUtils from the api util package. */
@Deprecated
public class EscapeUtils {

    /**
     * Escapes quotes and backslashes in a string.  Double
     * quotes are replaced with a backslash followed by a double
     * quote, and backslashes are replaced with a double backslash.
     * @param s The string to be escaped
     * @return The escaped string.
     */
    public static String escapeString(String s) {
        // We replace double quotes with a back slash followed
        // by a double quote.  We replace backslashes with a double
        // backslash
        if (s.indexOf('\"') == -1 && s.indexOf('\\') == -1) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 20);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '\\') {
                sb.append('\\');
                sb.append('\\');
            }
            else if (ch == '\"') {
                sb.append('\\');
                sb.append('\"');
            }
            else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }


    /**
     * @param s string to unescape
     * @return the unescaped string
     */
    public static String unescapeString(String s) {
        if (s.indexOf('\\') == -1) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '\\') {
                int j = i + 1;
                if (j < s.length()) {
                    char escCh = s.charAt(j);
                    if (escCh == '\\' || escCh == '\"') {
                        i++;
                        sb.append(escCh);
                    }
                }
                else {
                    sb.append('\\');
                }
            }
            else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }


    /** @param s
     * @return escaped string
     * @deprecated Use
     *             {@link org.semanticweb.owlapi.io.XMLUtils#escapeXML(CharSequence)} */
    @Deprecated
    public static String escapeXML(String s) {
        // double quote -- quot
        // ampersand    -- amp
        // less than    -- lt
        // greater than -- gt
        // apostrophe   -- apos
        StringBuilder sb = new StringBuilder(s.length() * 2);
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '<') {
                sb.append("&lt;");
            }
            else if (ch == '>') {
                sb.append("&gt;");
            }
            else if (ch == '\"') {
                sb.append("&quot;");
            }
            else if (ch == '&') {
                sb.append("&amp;");
            }
            else if (ch == '\'') {
                sb.append("&#");
                sb.append(Integer.toString(ch, 10));
                sb.append(';');
            }
            else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
