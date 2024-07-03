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
import com.braintribe.model.processing.query.smart.test.model.accessB.special.BookB;
import com.braintribe.model.processing.query.smart.test.model.smart.special.SmartBookB;
import com.braintribe.model.processing.query.smart.test.model.smart.special.SmartReaderA;
import com.braintribe.model.query.SelectQuery;

/**
 * See UnmappedPropertyType_PlannerTests
 */
public class UnmappedPropertyType_Tests extends AbstractSmartQueryTests {

	/** See UnmappedPropertyType_PlannerTests#selectUnmappedTypeProperty() */
	@Test
	public void selectUnmappedTypeProperty() {
		BookB b = bB.bookB("bb").create();
		bA.readerA("r").favoritePublicationTitle(b.getTitleB()).create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.select("r", "favoritePublication")
				.from(SmartReaderA.class, "r")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(smartBookB(b));
		assertNoMoreResults();
	}

	/** See UnmappedPropertyType_PlannerTests#selectUnmappedTypeProperty_Set() */
	@Test
	public void selectUnmappedTypeProperty_Set() {
		BookB b1 = bB.bookB("bb1").create();
		BookB b2 = bB.bookB("bb2").create();
		bA.readerA("r").favoritePublicationTitles(b1.getTitleB(), b2.getTitleB()).create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.select("r", "favoritePublications")
				.from(SmartReaderA.class, "r")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(smartBookB(b1));
		assertResultContains(smartBookB(b2));
		assertNoMoreResults();
	}

	/** See UnmappedPropertyType_PlannerTests#selectUnmappedTypePropertyWithSignatureCondition() */
	@Test
	public void selectUnmappedTypePropertyWithSignatureCondition() {
		BookB b = bB.bookB("bb").create();
		bA.readerA("r").favoritePublicationTitle(b.getTitleB()).create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.select("r", "favoritePublication")
				.from(SmartReaderA.class, "r")
				.where()
					.entitySignature("r", "favoritePublication").eq(SmartBookB.class.getName())
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(smartBookB(b));
		assertNoMoreResults();
	}

	/** See UnmappedPropertyType_PlannerTests#selectUnmappedTypePropertyWithEntityCondition() */
	@Test
	public void selectUnmappedTypePropertyWithEntityCondition() {
		BookB b = bB.bookB("bb").create();
		bA.readerA("r").favoritePublicationTitle(b.getTitleB()).create();

		SmartBookB sb = smartBookB(b);

		// @formatter:off
		SelectQuery selectQuery = query()		
				.select("r", "favoritePublication")
				.from(SmartReaderA.class, "r")
				.where()
					.property("r", "favoritePublication").eq().entity(sb)
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(sb);
		assertNoMoreResults();
	}

	/** See UnmappedPropertyType_PlannerTests#selectAsIsMappedPropertyAndNotSmartReferenceUseCase() */
	@Test
	public void selectAsIsMappedPropertyAndNotSmartReferenceUseCase() {
		ManualA m = bA.manualA("m").create();
		bA.readerA("r").favoriteManual(m).create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.select("r", "favoriteManual")
				.from(SmartReaderA.class, "r")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(smartManualA(m));
		assertNoMoreResults();
	}

	@Test
	public void selectIkpaPropertyWhereKeyPropertyIsUnmappedSmartOne() {
		BookB b = bB.bookB("bb").create();
		ReaderA r = bA.readerA("r").ikpaPublicationTitle(b.getTitleB()).create();

		SelectQuery selectQuery = query()		
				.select("b", "favoriteReader")
				.from(SmartBookB.class, "b")
				.done();

		evaluate(selectQuery);

		assertResultContains(smartReaderA(r));
		assertNoMoreResults();
	}

}
