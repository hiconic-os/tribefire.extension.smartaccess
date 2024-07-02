// ============================================================================
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
// ============================================================================
// Copyright BRAINTRIBE TECHNOLOGY GMBH, Austria, 2002-2022
// 
// This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either version 3 of the License, or (at your option) any later version.
// 
// This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
// 
// You should have received a copy of the GNU Lesser General Public License along with this library; See http://www.gnu.org/licenses/.
// ============================================================================
package com.braintribe.model.processing.query.smart.test.builder.special;

import static com.braintribe.utils.lcd.CollectionTools2.asSet;

import com.braintribe.model.processing.query.smart.test.builder.AbstractBuilder;
import com.braintribe.model.processing.query.smart.test.builder.SmartDataBuilder;
import com.braintribe.model.processing.query.smart.test.builder.repo.RepositoryDriver;
import com.braintribe.model.processing.query.smart.test.model.accessA.special.ManualA;
import com.braintribe.model.processing.query.smart.test.model.accessA.special.ReaderA;

/**
 * 
 */
public class ReaderABuilder extends AbstractBuilder<ReaderA, ReaderABuilder> {

	public static ReaderABuilder newInstance(SmartDataBuilder dataBuilder) {
		return new ReaderABuilder(dataBuilder.repoDriver());
	}

	public ReaderABuilder(RepositoryDriver repoDriver) {
		super(ReaderA.class, repoDriver);
	}

	public ReaderABuilder name(String value) {
		instance.setName(value);
		return this;
	}

	public ReaderABuilder favoritePublicationTitle(String value) {
		instance.setFavoritePublicationTitle(value);
		return this;
	}

	public ReaderABuilder favoritePublicationTitles(String... values) {
		instance.setFavoritePublicationTitles(asSet(values));
		return self;
	}

	public ReaderABuilder favoriteManual(ManualA value) {
		instance.setFavoriteManual(value);
		return this;
	}

	public ReaderABuilder ikpaPublicationTitle(String publicationTitle) {
		instance.setIkpaPublicationTitle(publicationTitle);
		return this;
	}

	// ##################################################
	// ## . . . . . . Weak-type Properties . . . . . . ##
	// ##################################################

	public ReaderABuilder favoriteManualTitle(String value) {
		instance.setFavoriteManualTitle(value);
		return this;
	}

	public ReaderABuilder favoriteManualTitles(String... values) {
		instance.setFavoriteManualTitles(asSet(values));
		return self;
	}

}
