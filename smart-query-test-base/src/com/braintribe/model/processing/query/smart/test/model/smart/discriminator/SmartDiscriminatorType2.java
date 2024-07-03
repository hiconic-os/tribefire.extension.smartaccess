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
package com.braintribe.model.processing.query.smart.test.model.smart.discriminator;

import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

/**
 * @see SmartDiscriminatorBase
 */

public interface SmartDiscriminatorType2 extends SmartDiscriminatorBase {

	EntityType<SmartDiscriminatorType2> T = EntityTypes.T(SmartDiscriminatorType2.class);

	final String DISC_TYPE2 = "type2";

	// @formatter:off
	String getType2Name();
	void setType2Name(String value);

	/** For special case testing, only Type2 has "discriminator" property mapped */
	String getDiscriminator();
	void setDiscriminator(String value);
	// @formatter:on

}
