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
package com.braintribe.model.query.smart.processing.eval.context;

import com.braintribe.model.processing.query.eval.api.QueryEvaluationContext;
import com.braintribe.model.processing.query.eval.api.Tuple;
import com.braintribe.model.processing.query.eval.context.ConditionEvaluator;
import com.braintribe.model.queryplan.filter.Condition;
import com.braintribe.model.queryplan.filter.ConditionType;
import com.braintribe.model.smartqueryplan.filter.SmartFullText;

/**
 * 
 */
public class SmartConditionEvaluator extends ConditionEvaluator {

	private static final SmartConditionEvaluator instance = new SmartConditionEvaluator();

	private SmartConditionEvaluator() {
	}

	public static SmartConditionEvaluator getInstance() {
		return instance;
	}

	@Override
	public boolean evaluate(Tuple tuple, Condition condition, QueryEvaluationContext context) {
		if (condition.conditionType() == ConditionType.fullText) {
			return SmartFulltextComparator.matches(tuple, (SmartFullText) condition);

		} else {
			return super.evaluate(tuple, condition, context);
		}

	}

}
