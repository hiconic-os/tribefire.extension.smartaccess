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
package com.braintribe.model.processing.query.smart.test.model;

import com.braintribe.model.accessdeployment.smart.meta.ConversionEnumConstantAssignment;
import com.braintribe.model.accessdeployment.smart.meta.EnumConstantAssignment;
import com.braintribe.model.accessdeployment.smart.meta.QualifiedEnumConstantAssignment;
import com.braintribe.model.meta.GmEnumConstant;
import com.braintribe.model.meta.GmEnumType;

/**
 * @see #provideAssignmentFor(GmEnumConstant)
 */
public abstract class EnumConstantMappingProvider {

	/** Provides {@link EnumConstantAssignment} for given {@link GmEnumConstant enum constant}. */
	public abstract EnumConstantAssignment provideAssignmentFor(GmEnumConstant enumConstant);

	public static EnumConstantMappingProvider STRING_CONVERSION(String suffix) {
		return new StringMappingProvider(suffix);
	}

	public static EnumConstantMappingProvider ENUM_CONVERSION(GmEnumType delegateEnum, String suffix) {
		return new DelegateEnumMappingProvider(delegateEnum, suffix);
	}

	private static class StringMappingProvider extends EnumConstantMappingProvider {
		private final String suffix;

		public StringMappingProvider(String suffix) {
			this.suffix = suffix;
		}

		@Override
		public EnumConstantAssignment provideAssignmentFor(GmEnumConstant enumConstant) {
			ConversionEnumConstantAssignment result = ConversionEnumConstantAssignment.T.create();
			result.setDelegateValue(enumConstant.getName() + suffix);

			return result;
		}
	}

	private static class DelegateEnumMappingProvider extends EnumConstantMappingProvider {
		private final GmEnumType delegateEnum;
		private final String suffix;

		public DelegateEnumMappingProvider(GmEnumType delegateEnum, String suffix) {
			this.delegateEnum = delegateEnum;
			this.suffix = suffix;
		}

		@Override
		public EnumConstantAssignment provideAssignmentFor(GmEnumConstant enumConstant) {
			QualifiedEnumConstantAssignment result = QualifiedEnumConstantAssignment.T.create();
			result.setDelegateEnumConstant(findConstant(enumConstant.getName() + suffix));

			return result;
		}

		private GmEnumConstant findConstant(String name) {
			for (GmEnumConstant enumConstant: delegateEnum.getConstants()) {
				if (enumConstant.getName().equals(name)) {
					return enumConstant;
				}
			}

			throw new RuntimeException(
					"Test configuration error. No enum constant of type '" + delegateEnum.getTypeSignature() + "' found for name: " + name);
		}
	}

}
