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
package com.braintribe.model.access.smart.test.query;

import org.junit.Test;

import com.braintribe.model.processing.query.smart.test.model.accessA.special.ManualA;
import com.braintribe.model.processing.query.smart.test.model.accessA.special.ReaderA;
import com.braintribe.model.processing.query.smart.test.model.smart.special.SmartManualA;
import com.braintribe.model.processing.query.smart.test.model.smart.special.SmartReaderA;
import com.braintribe.model.query.SelectQuery;

/**
 * See UnmappedPropertyType_PlannerTests
 */
public class UnmappedPropertyType_Weak_Tests extends AbstractSmartQueryTests {

	/** See UnmappedPropertyType_Weak_PlannerTests#selectUnmappedTypeProperty_Weak() */
	@Test
	public void selectUnmappedTypeProperty_Weak() {
		ManualA a = bA.manualA("ma").create();
		bA.readerA("r").favoriteManualTitle(a.getTitle()).create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.select("r", "weakFavoriteManual")
				.from(SmartReaderA.class, "r")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(smartManualA(a));
		assertNoMoreResults();
	}
	                                           
	/** See UnmappedPropertyType_Weak_PlannerTests#selectUnmappedTypeProperty_Weak_Set() */
	@Test
	@SuppressWarnings("unused")
	public void selectUnmappedTypeProperty_Weak_Set() {
		ManualA a1 = bA.manualA("ma1").create();
		ManualA a2 = bA.manualA("ma2").create();
		ManualA a3 = bA.manualA("ma3").create();
		bA.readerA("r").favoriteManualTitles(a1.getTitle(), a2.getTitle()).create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.select("r", "weakFavoriteManuals")
				.from(SmartReaderA.class, "r")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(smartManualA(a1));
		assertResultContains(smartManualA(a2));
		assertNoMoreResults();
	}

	@Test
	@SuppressWarnings("unused")
	public void selectUnmappedTypeProperty_WeakInverse_Set() {
		ReaderA r = bA.readerA("r").create();
		ManualA a1 = bA.manualA("ma1").manualString(r.getName()).create();
		ManualA a2 = bA.manualA("ma2").manualString(r.getName()).create();
		ManualA a3 = bA.manualA("ma3").manualString("something else").create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.select("r", "weakInverseFavoriteManuals")
				.from(SmartReaderA.class, "r")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(smartManualA(a1));
		assertResultContains(smartManualA(a2));
		assertNoMoreResults();
	}
	
	/** See UnmappedPropertyType_Weak_PlannerTests#selectUnmappedTypePropertyWithEntityCondition_Weak() */
	@Test
	public void selectUnmappedTypePropertyWithEntityCondition_Weak() {
		ManualA a = bA.manualA("ma").create();
		bA.readerA("r").favoriteManualTitle(a.getTitle()).create();

		SmartManualA sm = smartManualA(a);

		// @formatter:off
		SelectQuery selectQuery = query()		
				.select("r", "weakFavoriteManual")
				.from(SmartReaderA.class, "r")
				.where()
					.property("r", "weakFavoriteManual").eq().entity(sm)
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(sm);
		assertNoMoreResults();
	}

}
