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
package com.braintribe.model.query.smart.processing.eval.context.conversion;

import com.braintribe.model.accessdeployment.smart.meta.conversion.SmartConversion;
import com.braintribe.model.processing.smartquery.eval.api.ConversionDirection;
import com.braintribe.model.processing.smartquery.eval.api.SmartConversionExpert;

/**
 * Basic class for simple "to-string" {@link SmartConversionExpert}s.
 */
public abstract class AbstractToSimpleTypeExpert<T, C extends SmartConversion, S> implements SmartConversionExpert<C> {

	@Override
	public Object convertValue(C conversion, Object value, ConversionDirection direction) {
		if (value == null) {
			return null;
		}

		boolean valueIsDelegate = (direction == ConversionDirection.delegate2Smart);

		return convert(value, conversion, conversion.getInverse() == valueIsDelegate);
	}

	@SuppressWarnings("unchecked")
	protected Object convert(Object value, C conversion, boolean valueIsSimple) {
		if (valueIsSimple) {
			return parse((S) value, conversion);
		} else {
			return toSimpleValue((T) value, conversion);
		}
	}

	protected abstract T parse(S value, C conversion);

	/**
	 * @param conversion
	 *            actual instance of {@link SmartConversion} with possible parameters
	 */
	protected abstract S toSimpleValue(T value, C conversion);

}
