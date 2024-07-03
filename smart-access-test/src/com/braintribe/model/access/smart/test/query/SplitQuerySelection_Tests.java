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

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.braintribe.model.processing.query.smart.test.model.accessA.Address;
import com.braintribe.model.processing.query.smart.test.model.accessA.CarA;
import com.braintribe.model.processing.query.smart.test.model.accessA.CompanyA;
import com.braintribe.model.processing.query.smart.test.model.accessA.FlyingCarA;
import com.braintribe.model.processing.query.smart.test.model.accessA.PersonA;
import com.braintribe.model.processing.query.smart.test.model.accessA.special.BookA;
import com.braintribe.model.processing.query.smart.test.model.accessA.special.ManualA;
import com.braintribe.model.processing.query.smart.test.model.accessB.ItemB;
import com.braintribe.model.processing.query.smart.test.model.accessB.PersonB;
import com.braintribe.model.processing.query.smart.test.model.accessB.special.BookB;
import com.braintribe.model.processing.query.smart.test.model.smart.BasicSmartEntity;
import com.braintribe.model.processing.query.smart.test.model.smart.special.SmartBookA;
import com.braintribe.model.processing.query.smart.test.model.smart.special.SmartBookB;
import com.braintribe.model.processing.query.smart.test.model.smart.special.SmartManualA;
import com.braintribe.model.processing.query.smart.test.model.smart.special.SmartPublication;
import com.braintribe.model.processing.query.smart.test.model.smart.special.SmartReaderA;
import com.braintribe.model.query.OrderingDirection;
import com.braintribe.model.query.SelectQuery;

/**
 * See SplitQuerySelection_PlannerTests
 */
public class SplitQuerySelection_Tests extends AbstractSmartQueryTests {

	/** See SplitQuerySelection_PlannerTests#simpleEntitySelect() */
	@Test
	public void simpleEntitySelect() {
		BookA ba1 = bA.bookA("ba1").create();
		BookA ba2 = bA.bookA("ba2").create();
		BookB bb = bB.bookB("bb").create();
		ManualA ma = bA.manualA("ma").create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.from(SmartPublication.class, "p")
				.select("p")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(smartBookA(ba1));
		assertResultContains(smartBookA(ba2));
		assertResultContains(smartBookB(bb));
		assertResultContains(smartManualA(ma));
		assertNoMoreResults();
	}

	@Test
	public void selectingGenericEntity() {
		BookA ba = bA.bookA("ba").create();
		ManualA ma = bA.manualA("ma").create();
		PersonA pa = bA.personA("pa").create();
		CompanyA ca = bA.company("ca").create();
		Address aa = bA.address("street").create();
		CarA carA = bA.carA("car").create();
		FlyingCarA fcA = bA.flyingCarA("flying-car").create();

		PersonB pb = bB.personB("pb").create();
		ItemB ib = bB.item("ib").create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.from(BasicSmartEntity.class, "ge")
				.select("ge")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(smartBookA(ba));
		assertResultContains(smartManualA(ma));
		assertResultContains(smartPerson(pa));
		assertResultContains(smartCompany(ca));
		assertResultContains(smartAddress(aa));
		assertResultContains(smartCar(carA));
		assertResultContains(smartFlyingCar(fcA));

		assertResultContains(smartPerson(pb));
		assertResultContains(smartItem(ib));

		assertNoMoreResults();
	}

	/** See SplitQuerySelection_PlannerTests#selectPaginatedEntity() */
	@Test
	public void selectPaginatedEntity() {
		BookA ba1 = bA.bookA("ba1").create();
		BookB bb = bB.bookB("bb").create();
		ManualA ma = bA.manualA("ma").create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.from(SmartPublication.class, "b")
				.select("b")
				.paging(2, 1)
				.done();
		// @formatter:on

		evaluate(selectQuery);

		// this one is skipped due to pagination (we know it's this one cause the sources are accessed alphabetically)
		assertResultNotContains(smartBookA(ba1));
		assertResultContains(smartBookB(bb));
		assertResultContains(smartManualA(ma));
		assertNoMoreResults();
	}

	/** See SplitQuerySelection_PlannerTests#selectSimplySortedEntity() */
	@Test
	public void selectSimplySortedEntity() {
		BookA ba1 = bA.bookA("ba1").author("ccc").create();
		BookA ba2 = bA.bookA("ba2").author("aaa").create();
		ManualA ma = bA.manualA("ma").author("bbb").create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.from(SmartBookA.class, "b")
				.select("b")
				.orderBy().property("b", "author")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertNextResult(smartBookA(ba2));
		assertNextResult(smartManualA(ma));
		assertNextResult(smartBookA(ba1));
		assertNoMoreResults();
	}

	/** See SplitQuerySelection_PlannerTests#selectSimplySortedEntityProperty() */
	@Test
	public void selectSimplySortedEntityProperty() {
		bA.bookA("ba1").author("ccc").create();
		bA.bookA("ba2").author("aaa").create();
		bA.manualA("ma").author("bbb").create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.from(SmartBookA.class, "b")
				.select("b", "title")
				.orderBy().property("b", "author")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertNextResult("ba2");
		assertNextResult("ma");
		assertNextResult("ba1");
		assertNoMoreResults();
	}

	/** See SplitQuerySelection_PlannerTests#selectMultiSortedEntity() */
	@Test
	public void selectMultiSortedEntity() {
		bA.bookA("ba1").author("bbb").isbn("1").create();
		bA.bookA("ba2").author("aaa").isbn("2").create();
		bA.manualA("ma1").author("aaa").isbn("3").create();
		bA.manualA("ma2").author("bbb").isbn("4").create();

		// @formatter:off
		SelectQuery selectQuery = query()		
				.from(SmartBookA.class, "b")
				.select("b", "title")
				.select("b", "author")
				.orderByCascade()
					.property("b", "author")
					.dir(OrderingDirection.descending).property("b", "isbn")
				.close()
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertNextResult("ma1", "aaa");
		assertNextResult("ba2", "aaa");
		assertNextResult("ma2", "bbb");
		assertNextResult("ba1", "bbb");
		assertNoMoreResults();
	}

	/** See SplitQuerySelection_PlannerTests#selectCartesianProduct() */
	@Test
	public void selectCartesianProduct() {
		BookA ba1 = bA.bookA("ba1").author("bbb").isbn("1").create();
		BookA ba2 = bA.bookA("ba2").author("aaa").isbn("2").create();
		ManualA ma1 = bA.manualA("ma1").author("aaa").isbn("3").create();
		ManualA ma2 = bA.manualA("ma2").author("bbb").isbn("4").create();

		SmartBookA sb1 = smartBookA(ba1);
		SmartBookA sb2 = smartBookA(ba2);
		SmartManualA sm1 = smartManualA(ma1);
		SmartManualA sm2 = smartManualA(ma2);

		// @formatter:off
		SelectQuery selectQuery = query()		
				.select("b1")
				.select("b2")
				.from(SmartBookA.class, "b1")
				.from(SmartBookA.class, "b2")
				.where()
					.property("b1", "isbn").eq().property("b2", "isbn")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		assertResultContains(sb1, sb1);
		assertResultContains(sb2, sb2);
		assertResultContains(sm1, sm1);
		assertResultContains(sm2, sm2);
		assertNoMoreResults();
	}

	@Test
	public void selectCartesianProduct_NoRestriction() {
		BookA ba1 = bA.bookA("ba1").author("bbb").isbn("1").create();
		BookA ba2 = bA.bookA("ba2").author("aaa").isbn("2").create();
		ManualA ma1 = bA.manualA("ma1").author("aaa").isbn("3").create();
		ManualA ma2 = bA.manualA("ma2").author("bbb").isbn("4").create();

		SmartBookA sb1 = smartBookA(ba1);
		SmartBookA sb2 = smartBookA(ba2);
		SmartManualA sm1 = smartManualA(ma1);
		SmartManualA sm2 = smartManualA(ma2);

		// @formatter:off
		SelectQuery selectQuery = query()		
				.select("b1")
				.select("b2")
				.from(SmartBookA.class, "b1")
				.from(SmartBookA.class, "b2")
				.done();
		// @formatter:on

		evaluate(selectQuery);

		List<SmartBookA> smartBooks = Arrays.asList(sb1, sb2, sm1, sm2);
		for (SmartBookA b1: smartBooks) {
			for (SmartBookA b2: smartBooks) {
				assertResultContains(b1, b2);
			}
		}
		assertNoMoreResults();
	}

	@Test
	public void selectSplitProperty() {
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

	@Test
	public void selectSplitProperty_Set() {
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

	@Test
	public void selectSplitPropertyWithSignatureCondition() {
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

	/** See SplitQuerySelection_PlannerTests#selectSplitPropertyWithEntityCondition() */
	@Test
	public void selectSplitPropertyWithEntityCondition() {
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

}
