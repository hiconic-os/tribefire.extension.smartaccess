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
package com.braintribe.model.access.smart.query.fluent;

import com.braintribe.model.processing.query.fluent.SelectQueryBuilder;
import com.braintribe.model.smartqueryplan.queryfunctions.ResolveDelegateProperty;
import com.braintribe.model.smartqueryplan.queryfunctions.ResolveId;

public class SmartSelectQueryBuilder extends SelectQueryBuilder {

	public SmartSelectQueryBuilder selectId(String sourceAlias) {
		ResolveId resolveId = ResolveId.T.create();
		resolveId.setSource(acquireSource(sourceAlias));

		return (SmartSelectQueryBuilder) select(resolveId);
	}

	public SmartSelectQueryBuilder selectDelegateProperty(String sourceAlias, String delegateProperty) {
		ResolveDelegateProperty rdp = ResolveDelegateProperty.T.create();
		rdp.setSource(acquireSource(sourceAlias));
		rdp.setDelegateProperty(delegateProperty);

		return (SmartSelectQueryBuilder) select(rdp);
	}

	@Override
	public SmartSelectQueryBuilder from(String typeSignature, String alias) {
		return (SmartSelectQueryBuilder) super.from(typeSignature, alias);
	}

}
