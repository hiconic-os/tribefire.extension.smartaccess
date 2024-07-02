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
package com.braintribe.model.smartqueryplan.set;

import com.braintribe.model.generic.reflection.EntityType;
import com.braintribe.model.generic.reflection.EntityTypes;

import java.util.List;

import com.braintribe.model.accessdeployment.IncrementalAccess;

import com.braintribe.model.query.SelectQuery;
import com.braintribe.model.queryplan.set.TupleSetType;
import com.braintribe.model.smartqueryplan.ScalarMapping;

/**
 * What we actually do here is projecting delegate query results into scalar tuple components whose layout is defined by the Planner to have
 * everything which is needed for further filtering and projection (e.g building a target entity).
 * 
 * Note the batchSize property is optional. It is used for optimization of queries with an order-by clause. It can only be set by the
 * planner iff the {@link #getDelegateQuery() delegateQuery} also contains an order-by clause (otherwise you cannot really do bulks),
 * without pagination data (this pagination data is then set automatically by the evaluator, with limit being our batchSize and offset being
 * batchSize*iteration_counter).
 */
public interface DelegateQuerySet extends SmartTupleSet {

	EntityType<DelegateQuerySet> T = EntityTypes.T(DelegateQuerySet.class);

	IncrementalAccess getDelegateAccess();
	void setDelegateAccess(IncrementalAccess delegateAccess);

	SelectQuery getDelegateQuery();
	void setDelegateQuery(SelectQuery delegateQuery);

	List<ScalarMapping> getScalarMappings();
	void setScalarMappings(List<ScalarMapping> scalarMappings);

	Integer getBatchSize();
	void setBatchSize(Integer batchSize);

	@Override
	default TupleSetType tupleSetType() {
		return TupleSetType.extension;
	}

	@Override
	default SmartTupleSetType smartType() {
		return SmartTupleSetType.delegateQuerySet;
	}

}
