@file:Suppress("NOTHING_TO_INLINE")

package io.kotest.property

import io.kotest.matchers.shouldBe
import io.kotest.property.arbitrary.default
import io.kotest.property.internal.proptest

suspend fun <A, B> checkAll(
   genA: Gen<A>,
   genB: Gen<B>,
   property: suspend PropertyContext.(A, B) -> Unit
): PropertyContext = proptest(computeDefaultIteration(genA, genB), genA, genB, PropTestConfig(), property)

suspend fun <A, B> checkAll(
   config: PropTestConfig,
   genA: Gen<A>,
   genB: Gen<B>,
   property: suspend PropertyContext.(A, B) -> Unit
): PropertyContext = checkAll(computeDefaultIteration(genA, genB), config, genA, genB, property)

suspend fun <A, B> checkAll(
   iterations: Int,
   genA: Gen<A>,
   genB: Gen<B>,
   property: suspend PropertyContext.(A, B) -> Unit
): PropertyContext = proptest(iterations, genA, genB, PropTestConfig(), property)

suspend fun <A, B> checkAll(
   iterations: Int,
   config: PropTestConfig,
   genA: Gen<A>,
   genB: Gen<B>,
   property: suspend PropertyContext.(A, B) -> Unit
): PropertyContext = proptest(iterations, genA, genB, config, property)

suspend inline fun <reified A, reified B> checkAll(
   noinline property: suspend PropertyContext.(A, B) -> Unit
) = proptest(
   PropertyTesting.defaultIterationCount,
   Arb.default(),
   Arb.default(),
   PropTestConfig(),
   property
)

suspend inline fun <reified A, reified B> PropTest.checkAll(
   noinline property: suspend PropertyContext.(A, B) -> Unit
) = proptest(
   this.iterations ?: PropertyTesting.defaultIterationCount,
   Arb.default(),
   Arb.default(),
   this.toPropTestConfig(),
   property
)

suspend inline fun <reified A, reified B> checkAll(
   config: PropTestConfig,
   noinline property: suspend PropertyContext.(A, B) -> Unit
) = proptest(
   PropertyTesting.defaultIterationCount,
   Arb.default(),
   Arb.default(),
   config,
   property
)

suspend inline fun <reified A, reified B> checkAll(
   iterations: Int,
   noinline property: suspend PropertyContext.(A, B) -> Unit
) = proptest(
   iterations,
   Arb.default(),
   Arb.default(),
   PropTestConfig(),
   property
)

suspend inline fun <reified A, reified B> checkAll(
   iterations: Int,
   config: PropTestConfig,
   noinline property: suspend PropertyContext.(A, B) -> Unit
) = proptest(
   iterations,
   Arb.default(),
   Arb.default(),
   config,
   property
)

suspend fun <A, B> forAll(
   genA: Gen<A>,
   genB: Gen<B>,
   property: suspend PropertyContext.(A, B) -> Boolean
) = forAll(computeDefaultIteration(genA, genB), PropTestConfig(), genA, genB, property)

suspend fun <A, B> forAll(
   config: PropTestConfig = PropTestConfig(),
   genA: Gen<A>,
   genB: Gen<B>,
   property: suspend PropertyContext.(A, B) -> Boolean
) = forAll(computeDefaultIteration(genA, genB), config, genA, genB, property)

suspend fun <A, B> forAll(
   iterations: Int,
   genA: Gen<A>,
   genB: Gen<B>,
   property: suspend PropertyContext.(A, B) -> Boolean
) = forAll(iterations, PropTestConfig(), genA, genB, property)

suspend fun <A, B> forAll(
   iterations: Int,
   config: PropTestConfig,
   genA: Gen<A>,
   genB: Gen<B>,
   property: suspend PropertyContext.(A, B) -> Boolean
) = proptest(iterations, genA, genB, config) { a, b -> property(a, b) shouldBe true }

suspend inline fun <reified A, reified B> forAll(
   crossinline property: PropertyContext.(A, B) -> Boolean
): PropertyContext = forAll(PropertyTesting.defaultIterationCount, PropTestConfig(), property)

suspend inline fun <reified A, reified B> forAll(
   config: PropTestConfig = PropTestConfig(),
   crossinline property: PropertyContext.(A, B) -> Boolean
): PropertyContext = forAll(PropertyTesting.defaultIterationCount, config, property)

suspend inline fun <reified A, reified B> forAll(
   iterations: Int,
   crossinline property: PropertyContext.(A, B) -> Boolean
) = forAll(iterations, PropTestConfig(), property)

suspend inline fun <reified A, reified B> forAll(
   iterations: Int,
   config: PropTestConfig,
   crossinline property: PropertyContext.(A, B) -> Boolean
) = proptest<A, B>(
   iterations,
   Arb.default(),
   Arb.default(),
   config
) { a, b -> property(a, b) shouldBe true }

suspend fun <A, B> forNone(
   genA: Gen<A>,
   genB: Gen<B>,
   property: suspend PropertyContext.(A, B) -> Boolean
) = forNone(computeDefaultIteration(genA, genB), PropTestConfig(), genA, genB, property)

suspend fun <A, B> forNone(
   config: PropTestConfig = PropTestConfig(),
   genA: Gen<A>,
   genB: Gen<B>,
   property: suspend PropertyContext.(A, B) -> Boolean
) = forNone(computeDefaultIteration(genA, genB), config, genA, genB, property)

suspend fun <A, B> forNone(
   iterations: Int,
   genA: Gen<A>,
   genB: Gen<B>,
   property: suspend PropertyContext.(A, B) -> Boolean
) = forNone(iterations, PropTestConfig(), genA, genB, property)

suspend fun <A, B> forNone(
   iterations: Int,
   config: PropTestConfig,
   genA: Gen<A>,
   genB: Gen<B>,
   property: suspend PropertyContext.(A, B) -> Boolean
) = proptest(iterations, genA, genB, config) { a, b -> property(a, b) shouldBe false }

suspend inline fun <reified A, reified B> forNone(
   crossinline property: PropertyContext.(A, B) -> Boolean
): PropertyContext = forNone(PropertyTesting.defaultIterationCount, PropTestConfig(), property)

suspend inline fun <reified A, reified B> forNone(
   config: PropTestConfig = PropTestConfig(),
   crossinline property: PropertyContext.(A, B) -> Boolean
): PropertyContext = forNone(PropertyTesting.defaultIterationCount, config, property)

suspend inline fun <reified A, reified B> forNone(
   iterations: Int,
   crossinline property: PropertyContext.(A, B) -> Boolean
) = forNone(iterations, PropTestConfig(), property)

suspend inline fun <reified A, reified B> forNone(
   iterations: Int,
   config: PropTestConfig,
   crossinline property: PropertyContext.(A, B) -> Boolean
) = proptest<A, B>(
   iterations,
   Arb.default(),
   Arb.default(),
   config
) { a, b -> property(a, b) shouldBe false }
