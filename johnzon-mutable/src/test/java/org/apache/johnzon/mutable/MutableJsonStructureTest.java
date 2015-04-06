/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.johnzon.mutable;

import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;

import junit.framework.TestCase;

/**
 * @author Hendrik Saly
 */
public class MutableJsonStructureTest extends TestCase {

    public MutableJsonStructureTest(final String testName) {
        super(testName);
    }

    public void testSimple() throws Exception {
        final Reader wikiReader = new InputStreamReader(MutableJsonStructureTest.class.getResourceAsStream("/wiki.json"));
        final JsonReader reader = Json.createReader(wikiReader);
        final MutableJsonStructure ms = MutableJsonStructureFactory.toMutableJsonStructure(reader.readObject());
        reader.close();
        assertNotNull(ms);
        assertEquals(5, ms.size());

        ms.add("test", "val").add("num", 2);

        assertEquals(7, ms.size());

        //roundtrip tests
        final JsonStructure jsonStructure = ms.toJsonStructure();
        final MutableJsonStructure ms2 = MutableJsonStructureFactory.toMutableJsonStructure(jsonStructure);
        final JsonStructure jsonStructure2 = ms2.toJsonStructure();
        assertEquals(ms, ms2);
        assertEquals(jsonStructure, jsonStructure2);

    }

    public void testVarious() throws Exception {
        final Reader wikiReader = new InputStreamReader(MutableJsonStructureTest.class.getResourceAsStream("/wiki.json"));
        final JsonReader reader = Json.createReader(wikiReader);
        final MutableJsonStructure ms = MutableJsonStructureFactory.toMutableJsonStructure(reader.readObject());
        assertFalse(ms.isJsonArray());

        try {
            ms.getKeys().add("test");
            fail();
        } catch (final UnsupportedOperationException e) {
            //expected
        }

        assertNotSame(ms, ms.copy());
        assertTrue(ms.isLeaf("age"));
        assertFalse(ms.isLeaf("address"));
        assertFalse(ms.isLeafNull("firstName"));
        assertTrue(ms.exists("phoneNumber"));
        assertFalse(ms.exists("eMail"));
        assertEquals(1, ms.get("phoneNumber").get(1).getAncestor().getIndex());
        assertNull(ms.getParent());
        assertEquals("Smith", ms.getLeafAsString("lastName"));
        assertEquals("NY", ms.get("address").getLeafAsString("state"));
        assertEquals(5, ms.getKeys().size());
        assertEquals(5, ms.size());
        assertEquals(4, ms.get("address").size());
        ms.add("additionalAddress", ms.get("address").copy().remove("city").set("state", "CA"));
        ms.set(ms.copy().remove("phoneNumber"));
        assertEquals(5, ms.size());
    }

    public void testEmpty() throws Exception {
        final MutableJsonStructure ms = MutableJsonStructureFactory.createNewMutableArray();
        assertTrue(ms.isJsonArray());

        try {
            assertTrue(ms.isLeaf("age"));
            fail();
        } catch (final JsonException e) {
            //expected
        }

        assertNull(ms.getParent());
        ms.add("test").add(1).add(0, 0);
        ms.set(1, ms.copy());
        ms.get(1).remove(2);
        assertEquals("[0,[0,\"test\"],1]", ms.toJsonStructure().toString());
    }

    public void testMutate() throws Exception {
        final Reader wikiReader = new InputStreamReader(MutableJsonStructureTest.class.getResourceAsStream("/wiki.json"));
        final JsonReader reader = Json.createReader(wikiReader);
        final MutableJsonStructure ms = MutableJsonStructureFactory.toMutableJsonStructure(reader.readObject());
        reader.close();
        assertNotNull(ms);
        assertEquals(5, ms.size());

        final MutableJsonStructure m = ms.set("firstName", "Mister").set("lastName", "Spock").set("age", "unknown").get("address")
                .add("deceased", JsonValue.TRUE).getParent().get("phoneNumber").add(2, JsonValue.NULL).get(1).set("number", "000")
                .getParent().remove(0).getParent();

        System.out.println(pretty(ms));

        final Reader wikiMutatedReader = new InputStreamReader(MutableJsonStructureTest.class.getResourceAsStream("/wiki_mutated.json"));
        final JsonReader mutatedReader = Json.createReader(wikiMutatedReader);
        final JsonObject mutatedWikiObject = mutatedReader.readObject();
        mutatedReader.close();
        assertEquals(ms.toJsonStructure(), mutatedWikiObject);

    }

    public void testPointer() throws Exception {
        final Reader wikiReader = new InputStreamReader(MutableJsonStructureTest.class.getResourceAsStream("/facebook.json"));
        final JsonReader reader = Json.createReader(wikiReader);
        final JsonObject object = reader.readObject();
        final MutableJsonStructure ms = MutableJsonStructureFactory.toMutableJsonStructure(object);
        reader.close();
        assertNotNull(ms);
        assertEquals(2, ms.size());
        assertEquals("[data, paging]", ms.getKeys().toString());

        final String comment = ms.get("data").get(1).get("actions").get(0).getLeafAsString("name");
        assertEquals("Comment", comment);

    }

    public void testFullJson() throws Exception {
        final JsonObject objectLeaf = Json.createObjectBuilder().add("string", "abcdef").add("byte", (byte) 1).add("short", (short) 1)
                .add("int", 1).add("long", 1L).add("bigdecimal", new BigDecimal("1.23456")).add("biginteger", new BigInteger("100000"))
                .add("float", 1.234f).add("double", 1.234d).add("bool", false).build();

        final JsonArray arrayLeaf = Json.createArrayBuilder().add("xyzxyz").add((byte) -3).add((short) -3).add(-3).add(-3L)
                .add(new BigDecimal("-3.23456")).add(new BigInteger("-300000")).add(-3.234f).add(-3.234d).add(true).build();

        final JsonObject nested1 = Json.createObjectBuilder().add("anobject", objectLeaf).add("anarray", arrayLeaf).build();

        final JsonArray nested2 = Json.createArrayBuilder().add(nested1).add(nested1).build();

        final JsonObject nested3 = Json.createObjectBuilder().add("anobject_2", nested2).add("anarray_2", arrayLeaf)
                .add("bd", BigDecimal.ONE).build();

        //roundtrip tests
        final MutableJsonStructure nested3Mutable = MutableJsonStructureFactory.toMutableJsonStructure(nested3);
        final JsonStructure nested3Roundtrip = nested3Mutable.toJsonStructure();
        final MutableJsonStructure nested3MutableRoundtrip = MutableJsonStructureFactory.toMutableJsonStructure(nested3Roundtrip);
        assertEquals(nested3Roundtrip, nested3);
        assertEquals(nested3Roundtrip.toString(), nested3.toString());
        assertEquals(nested3MutableRoundtrip, nested3Mutable);
        assertEquals(nested3MutableRoundtrip.toString(), nested3Mutable.toString());
        assertEquals(nested3MutableRoundtrip.toString(), nested3.toString());
    }

    protected static String pretty(final MutableJsonStructure value) {
        return pretty(value.toJsonStructure());
    }

    protected static String pretty(final JsonValue value) {
        final Map config = new HashMap();
        config.put(JsonGenerator.PRETTY_PRINTING, true);
        final StringWriter sw = new StringWriter();
        Json.createGeneratorFactory(config).createGenerator(sw).writeStartArray().write(value).writeEnd().close();
        sw.flush();
        return (value.getValueType() + sw.toString().substring(2).substring(0, sw.toString().length() - 3).replace("\n    ", "\n"));
    }

}
