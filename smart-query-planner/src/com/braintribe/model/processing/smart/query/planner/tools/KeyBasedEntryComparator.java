// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// ============================================================================
package com.braintribe.model.processing.smart.query.planner.tools;

import static com.braintribe.utils.lcd.CollectionTools2.newList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 */
public class KeyBasedEntryComparator<K extends Comparable<K>, V> implements Comparator<Map.Entry<K, V>> {

	@SuppressWarnings("rawtypes")
	public static final KeyBasedEntryComparator<?, ?> INSTANCE = new KeyBasedEntryComparator();

	public static <K extends Comparable<K>, V> KeyBasedEntryComparator<K, V> instance() {
		return (KeyBasedEntryComparator<K, V>) INSTANCE;
	}

	public static <K extends Comparable<K>, V> List<Map.Entry<K, V>> sortMapEntries(Map<K, V> map) {
		List<Map.Entry<K, V>> result = newList(map.entrySet());
		Collections.sort(result, KeyBasedEntryComparator.<K, V> instance());

		return result;
	}

	private KeyBasedEntryComparator() {
	}

	@Override
	public int compare(Entry<K, V> e1, Entry<K, V> e2) {
		return e1.getKey().compareTo(e2.getKey());
	}

}
