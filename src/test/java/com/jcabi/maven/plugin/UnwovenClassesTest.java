/**
 * Copyright (c) 2012-2015, jcabi.com
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the jcabi.com nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.jcabi.maven.plugin;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Test;

/**
 * Unit tests for {@UnwovenClasses).
 * @author Mihai Andronache (amihaiemil@gmail.com)
 * @version $Id$
 * @checkstyle MultipleStringLiteralsCheck (150 lines)
 *
 */
public final class UnwovenClassesTest {

    /**
     * Delete the created directories after each test.
     * @throws Exception If something goes wrong.
     */
    @After
    public void clean() throws Exception {
        FileUtils.deleteDirectory(new File("src/test/resources/unwoven"));
        FileUtils.deleteDirectory(new File("src/test/resources/unwoven-test"));
    }

    /**
     * UnwovenClasses can copy compiled classes to a destination directory.
     * @throws Exception If something goes wrong
     */
    @Test
    public void copiesUnwovenClasses() throws Exception {
        final File classes = new File("src/test/resources/classes");
        final File unwoven = new File("src/test/resources/unwoven");
        new UnwovenClasses(
            unwoven,
            classes,
            LifecyclePhase.PROCESS_CLASSES.id()
        ).copy();
        MatcherAssert.assertThat(
            new File("src/test/resources/unwoven/MyPojo.txt").exists(),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new File("src/test/resources/unwoven/MySecondPojo.txt").exists(),
            Matchers.is(true)
        );
    }

    /**
     * UnwoveClasses can copy test classes to a destination directory.
     * @throws Exception If something goes wrong.
     */
    @Test
    public void copiesUnwovenTestClasses() throws Exception {
        final File classes = new File("src/test/resources/classes");
        final File unwoven = new File("src/test/resources/unwoven");
        new UnwovenClasses(
            unwoven,
            classes,
            LifecyclePhase.PROCESS_TEST_CLASSES.id()
        ).copy();
        MatcherAssert.assertThat(
            new File("src/test/resources/unwoven-test/MyPojo.txt").exists(),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new File(
                "src/test/resources/unwoven-test/MySecondPojo.txt"
            ).exists(),
            Matchers.is(true)
        );
    }
}