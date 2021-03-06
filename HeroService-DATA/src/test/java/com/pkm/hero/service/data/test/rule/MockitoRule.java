package com.pkm.hero.service.data.test.rule;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mockito.MockitoAnnotations;

public class MockitoRule implements TestRule {
	private final Object target;

	public MockitoRule(Object target) {
		this.target = target;
	}

	public Statement apply(final Statement base, Description description) {
		return new Statement() {
			@Override
			public void evaluate() throws Throwable {
				MockitoAnnotations.initMocks(target);
				base.evaluate();
			}
		};
	}
}
