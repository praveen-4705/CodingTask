package com.test.suite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.test.scripts.TestScripts;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TestScripts.class })
public class TestSuite {

}
