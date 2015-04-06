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

import java.util.Set;

//CHECKSTYLE:OFF
import javax.json.JsonException;
//CHECKSTYLE:ON
import javax.json.JsonStructure;
import javax.json.JsonValue;

/**
 * A mutable and navigable object representation of a JSON text.
 * <p>
 * The leafs of this tree are immutable JsonValues and are never of {@code JsonValue.ValueType} ARRAY or OBJECT but NUMBER or STRING or TRUE
 * or FALSE or NULL.
 * <p>
 * The mutation methods provides functionality for
 * 
 * <ul>
 * <li>adding either a {@code JsonValue} or a {@code MutableJsonStructure} through <code>add()</code></li>
 * <li>setting either a {@code JsonValue} or a {@code MutableJsonStructure} through <code>set()</code></li>
 * <li>removing either a {@code JsonValue} or a {@code MutableJsonStructure} through <code>remove()</code></li>
 * </ul>
 * 
 * <p>
 * The basic usage pattern for this class is:
 * 
 * <pre>
 * <code>
 * JsonObject jsonObject = ...;
 * MutableJsonStructure mutableJsonObject = jsonObject.toMutableJsonStructure();
 * mutableJsonObject.get(2).add("new_key","new_value").remove("unused_key");
 * JsonString oldPhoneNumber = (JsonString) mutableJsonObject.getLeaf("phoneNumber");
 * mutableJsonObject.set("phoneNumber", "555-4312");
 * JsonObject mutatedJsonObject = (JsonObject) mutableJsonObject.toJsonStructure();
 * </code>
 * </pre>
 * 
 * A new mutable tree can also be obtained with the static method MutableJsonStructure.createNewMutableObject() or
 * MutableJsonStructure.createNewMutableArray()
 * 
 * With every {@code MutableJsonStructure} an {@code Ancestor} is associated. For the top level structure the ancestor is null. Through the
 * {@code Ancestor} the position within the parent structure (as well as the parent itself) can be obtained. Using the {@code Ancestor} its
 * also possible to navigate bottom-up.
 * 
 * <p>
 * Top-down navigation is possible by using the <code>get(int)</code> (within a JSON array) or <code>get(String)</code> (within a JSON
 * object) method.
 * <ul>
 * <li>In a JSON array the array size can be determined by using <code>size()</code>.</li>
 * <li>In a JSON object the key names can be determined by using <code>getKeys()</code>.</li>
 * </ul>
 * 
 * Bottom-up navigation is possible by using <code>getParent()</code>
 * 
 * <p>
 * <b>Warning: This mutable representation will consume at least the same amount of memory then the immutable structure.</b>
 */
//@experimental
public interface MutableJsonStructure {

    /**
     * Represent the ancestor of a {@code MutableJsonStructure}. Contains also
     * the information to which key (if ancestor is an JSON object) or index (if
     * ancestor is an JSON array) the mutable structure is attached.
     */
    public interface Ancestor {

        /**
         * The ancestor index (only valid if this ancestor is an JSON array)
         * 
         * @return The zero-based ancestor index or -1 if this ancestor is a
         *         JSON object
         */
        int getIndex();

        /**
         * The ancestor key name (only valid if this ancestor is an JSON object)
         * 
         * @return The ancestor key name or {@code null} if this ancestor is a
         *         JSON array
         */
        String getKey();

        /**
         * The ancestor structure (parent)
         * 
         * @return the ancestor mutable structure
         */
        MutableJsonStructure getMutableJsonStructure();

        /**
         * Is this ancestor a JSON array (or an JSON object)?
         * 
         * @return true if the ancestor is a JSON array, false if JSON object
         */
        boolean isJsonArray();
    }

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure add(int index, JsonValue value);

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure add(int index, MutableJsonStructure value);

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure add(int index, Number value);

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure add(int index, String value);

    /**
     * Append the given value to this JSON array
     * 
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure add(JsonValue value);

    /**
     * Append the given value to this JSON array
     * 
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure add(MutableJsonStructure value);

    /**
     * Append the given value to this JSON array
     * 
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure add(Number value);

    /**
     * Append the given value to this JSON array
     * 
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure add(String value);

    /**
     * analogous, TBD
     * 
     * @param key
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure add(String key, JsonValue value);

    /**
     * Add (insert) the value of the given key
     * 
     * @param key
     *            given key
     * @param value
     *            new value which should be added
     * @return this {@code MutableJsonStructure}
     * @throws JsonException
     *             if current structure is not an JSON object
     * @throws NullPointerException
     *             if key or value is null
     */
    MutableJsonStructure add(String key, MutableJsonStructure value);

    /**
     * analogous, TBD
     * 
     * @param key
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure add(String key, Number value);

    /**
     * analogous, TBD
     * 
     * @param key
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure add(String key, String value);

    /**
     * Returns a deep copy of this structure. Modifying the content of the
     * returned structure does not affect each other at all.
     * 
     * @return the copy
     */
    MutableJsonStructure copy();

    /**
     * Compares the specified object with this {@code MutableJsonStructure} for
     * equality. Returns {@code true} if and only if the specified object is
     * also a {@code JMutableJsonStructure}, and they represents the same
     * structure or JSON value (leaf)
     *
     * @param obj
     *            the object to be compared for equality with this {@code MutableJsonStructure}
     * @return {@code true} if the specified object is equal to this {@code JMutableJsonStructure}
     */
    @Override
    boolean equals(Object obj);

    /**
     * Check if the given index exists.
     * 
     * @param index
     *            given index
     * @return true if index &lt; size(), false otherwise
     * @throws JsonException
     *             if current structure is not an JSON array
     */
    boolean exists(int index);

    /**
     * Check if given key exists.
     * 
     * @param key
     *            given key
     * @return true if getKeys().contains(key), false otherwise
     * @throws JsonException
     *             if current structure is not an JSON object
     */
    boolean exists(String key);

    /**
     * Get the {@code MutableJsonStructure} denoted by the index
     * 
     * @param index
     *            the index
     * @return the {@code MutableJsonStructure} denoted by the index
     * @throws JsonException
     *             if this {@code MutableJsonStructure} is not an JSON array or
     *             index is out of range
     */
    MutableJsonStructure get(int index);

    /**
     * Get the {@code MutableJsonStructure} denoted by the key name
     * 
     * @param key
     *            the key name
     * @return the {@code MutableJsonStructure} denoted by the key name
     * @throws JsonException
     *             if this {@code MutableJsonStructure} is not an JSON object or
     *             key does not exist
     * @throws NullPointerException
     *             if key is null
     */
    MutableJsonStructure get(String key);

    /**
     * Get the ancestor of this {@code MutableJsonStructure}
     * 
     * @return the ancestor or {@code null} if this is the top level structure
     */
    Ancestor getAncestor();

    /**
     * Get the key names for the current JSON object
     * 
     * @return the key names for the current JSON object as an immutable {@link Set}
     * @throws JsonException
     *             if this {@code MutableJsonStructure} is not an JSON object
     */
    Set<String> getKeys();

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @return TBD
     */
    JsonValue getLeaf(int index);

    /**
     * Get the {@code JsonValue} denoted by the key name
     * 
     * @param key
     *            the key name
     * @return the {@code JsonValue} denoted by the key name
     * @throws JsonException
     *             if the key does not point to JsonValue (leaf) or key does not
     *             exist
     * @throws NullPointerException
     *             if key is null
     */
    JsonValue getLeaf(String key);

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @return TBD
     */
    boolean getLeafAsBoolean(int index);

    /**
     * analogous, TBD
     * 
     * @param key
     *            TBD
     * @return TBD
     */
    boolean getLeafAsBoolean(String key);

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @return TBD
     */
    int getLeafAsInt(int index);

    /**
     * analogous, TBD
     * 
     * @param key
     *            TBD
     * @return TBD
     */
    int getLeafAsInt(String key);

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @return TBD
     */
    String getLeafAsString(int index);

    /**
     * analogous, TBD
     * 
     * @param key
     *            TBD
     * @return TBD
     */
    String getLeafAsString(String key);

    /**
     * Shortcut for <code>getAncestor().getMutableJsonStructure()</code>
     * 
     * @return the parent structure or {@code null} if this is the top level
     *         structure
     */
    MutableJsonStructure getParent();

    /**
     * Returns the hash code value for this {@code MutableJsonStructure} object.
     * The hash code of a {@code MutableJsonStructure} object is defined to be
     * the hashcode of the underlying JSON object or JSON array or JsonValue
     * (leaf).
     *
     * @return the hash code value for this {@code MutableJsonStructure} object
     */
    @Override
    int hashCode();

    /**
     * Is this {@code MutableJsonStructure} a JSON array (or an JSON object)
     * 
     * @return true if its a JSON array, false if JSON object
     */
    boolean isJsonArray();

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @return TBD
     */
    boolean isLeaf(int index);

    /**
     * Check if the given key's value is a leaf (or a mutable structure)
     * 
     * @param key
     *            given key
     * @return true if the given key key's value is a {@code JsonValue}
     */
    boolean isLeaf(String key);

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @return TBD
     */
    boolean isLeafNull(int index);

    /**
     * analogous, TBD
     * 
     * @param key
     *            TBD
     * @return TBD
     */
    boolean isLeafNull(String key);

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @return TBD
     */
    MutableJsonStructure remove(int index);

    /**
     * Remove (delete) the value of the given key
     * 
     * @param key
     *            given key
     * @return this {@code MutableJsonStructure}
     * @throws JsonException
     *             if the key does not exist or the current structure is not an
     *             JSON object
     * @throws NullPointerException
     *             if key is null
     */
    MutableJsonStructure remove(String key);

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure set(int index, JsonValue value);

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure set(int index, MutableJsonStructure value);

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure set(int index, Number value);

    /**
     * analogous, TBD
     * 
     * @param index
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure set(int index, String value);

    /**
     * This replaces the current {@code MutableJsonStructure} value with the
     * given one. This does NOT change object references.
     * 
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure set(MutableJsonStructure value);

    /**
     * analogous, TBD
     * 
     * @param key
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure set(String key, JsonValue value);

    /**
     * Set (replace) the value of the given key with the given value
     * 
     * @param key
     *            given key
     * @param value
     *            replacement value
     * @return this {@code MutableJsonStructure}
     * @throws JsonException
     *             if the key does not exist or the current structure is not an
     *             JSON object
     * @throws NullPointerException
     *             if key or value is null
     */
    MutableJsonStructure set(String key, MutableJsonStructure value);

    /**
     * analogous, TBD
     * 
     * @param key
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure set(String key, Number value);

    /**
     * analogous, TBD
     * 
     * @param key
     *            TBD
     * @param value
     *            TBD
     * @return TBD
     */
    MutableJsonStructure set(String key, String value);

    /**
     * Determine the size of this mutable structure (number of keys for a JSON
     * object, number of array members for a JSON array)
     * 
     * @return the size of the JSON object or JSON array
     * @throws JsonException
     *             TBD
     */
    int size();

    /**
     * Convert this {@code MutableJsonStructure} in a immutable {@code JsonStructure} All modifications on this {@code MutableJsonStructure}
     * which are done after calling this method are
     * not reflected to the returned value
     * 
     * @return the immutable {@code JsonStructure}
     */
    JsonStructure toJsonStructure();

    /**
     * Returns JSON text for this mutable JSON value.
     *
     * @return JSON text
     */
    @Override
    String toString();
}
