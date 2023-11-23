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
package com.braintribe.model.query.smart.processing.eval.context.function;

import java.util.Map;

import com.braintribe.model.processing.query.eval.api.QueryEvaluationContext;
import com.braintribe.model.processing.query.eval.api.Tuple;
import com.braintribe.model.processing.query.eval.api.function.QueryFunctionExpert;
import com.braintribe.model.queryplan.value.Value;
import com.braintribe.model.smartqueryplan.queryfunctions.ResolveId;

/**
 * 
 */
public class ResolveIdExpert implements QueryFunctionExpert<ResolveId> {

	public static final ResolveIdExpert INSTANCE = new ResolveIdExpert();

	private ResolveIdExpert() {
	}

	@Override
	public Object evaluate(Tuple tuple, ResolveId riFunction, Map<Object, Value> operandMappings, QueryEvaluationContext context) {
		throw new UnsupportedOperationException("This function is not supposed to be evaluated by the expert!");
	}

}
