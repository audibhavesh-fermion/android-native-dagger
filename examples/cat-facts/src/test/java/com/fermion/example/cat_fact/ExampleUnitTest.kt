package com.fermion.example.cat_fact
//
//import com.fermion.android.dagger.processor.BuilderProcessor
//import com.fermion.android.dagger.processor.BuilderProcessorProvider
//import com.fermion.android.dagger.processor.DaggerBaseView
//import com.fermion.android.dagger.processor.InjectView
//import com.fermion.example.cat_fact.ui.facts.FactsFragment
//import com.google.devtools.ksp.closestClassDeclaration
//import com.google.devtools.ksp.processing.CodeGenerator
//import com.google.devtools.ksp.processing.KSPLogger
//import com.google.devtools.ksp.processing.Resolver
//import com.google.devtools.ksp.processing.SymbolProcessor
//import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
//import com.google.devtools.ksp.processing.SymbolProcessorProvider
//import com.google.devtools.ksp.symbol.KSAnnotated
//import com.google.devtools.ksp.symbol.KSClassDeclaration
//import com.google.devtools.ksp.symbol.KSNode
//import com.google.devtools.ksp.validate
//import com.tschuchort.compiletesting.KotlinCompilation
//import com.tschuchort.compiletesting.SourceFile
//import com.tschuchort.compiletesting.kspIncremental
//import com.tschuchort.compiletesting.kspWithCompilation
//import com.tschuchort.compiletesting.symbolProcessorProviders
//
//import com.google.devtools.ksp.closestClassDeclaration
//import com.google.devtools.ksp.validate
//import com.squareup.kotlinpoet.ClassName
//import com.squareup.kotlinpoet.FileSpec
//import com.squareup.kotlinpoet.FunSpec
//import com.squareup.kotlinpoet.KModifier
//import com.squareup.kotlinpoet.TypeSpec
//import com.squareup.kotlinpoet.ksp.toClassName
//

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

//    @Test
//    fun setup() {
//        val factsFragment =
//            SourceFile.fromPath(File("F:\\OpenSource\\ProjectTemplates\\examples\\cat-facts\\src\\main\\java\\com\\fermion\\example\\cat_fact\\ui\\facts\\FactsFragment.kt"))
//
//        //   val baseFragment =
//        //       SourceFile.fromPath(File("F:\\OpenSource\\ProjectTemplates\\library\\base\\src\\main\\java\\com\\fermion\\android\\base\\view\\BaseFragment.kt"))
//
//        val baseFragment = SourceFile.kotlin(
//            "BaseFragment.kt", """
//            package com.fermion.android.base.view
//            import com.fermion.android.dagger.processor.DaggerBaseView
//
//            @DaggerBaseView
//            class BaseFragment{
//
//            }
//        """.trimIndent()
//        )
//        val compilation = KotlinCompilation().apply {
//            workingDir = File("F:\\OpenSource\\ProjectTemplates\\KotlinTemp")
////            sources = listOf(baseFragment)
////            sources = listOf(baseFragment)
//            symbolProcessorProviders = listOf(TestBuilderProcessorProvider())
//
//            //workingDir =
//            inheritClassPath = true
//            verbose = true
//            kspWithCompilation = true
//            //messageOutputStream = System.out
//            kspIncremental = true
//        }
//        val compilationResult = compilation.compile()
//        println("sdsds " + compilationResult.messages)
////        assertEquals(KotlinCompilation.ExitCode.OK, compilationResult.exitCode)
//
//        // The next line leads to java.lang.ClassNotFoundException
////        compilationResult.classLoader.loadClass("test.pack.TestClassDslBuilder")
//    }
}
//
//class TestBuilderProcessorProvider : SymbolProcessorProvider {
//    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
//        return TestBuilderProcessor(environment.codeGenerator, environment.logger)
//    }
//}
//
//
//class TestBuilderProcessor(
//    private val codeGenerator: CodeGenerator, private val logger: KSPLogger
//) : SymbolProcessor {
//    override fun onError() {
//        println("EROROROR")
//        super.onError()
//    }
//
//    override fun finish() {
//        super.finish()
//        println("FINISH")
//    }
//    override fun process(resolver: Resolver): List<KSAnnotated> {
//        println("Hellp")
////        val factories = getFactories(resolver)
////        logger.info("Classes  $factories")
////        val data = getElements(resolver, factories)
////        data.forEach {
////            genFile(it.key, it.value).writeTo(codeGenerator, Dependencies(true))
////        }
//        return emptyList()
//    }
//
//    private fun genFile(key: ClassName, list: List<ClassName>): FileSpec {
//        val packageName = key.packageName
//        val fileName = key.simpleName + "Module"
//        println(key.simpleName)
//        return FileSpec.builder(packageName, fileName).addType(
//            TypeSpec.interfaceBuilder(fileName).addAnnotation(
//                ClassName(
//                    "dagger", "Module"
//                )
//            ).addFunction(
//                FunSpec.builder("contribute${fileName}")
//                    .addAnnotation(ClassName("com.dgv.base.scopes", "FragmentScope")).addAnnotation(
//                        ClassName(
//                            "dagger.android", "ContributesAndroidInjector"
//                        )
//                    ).addModifiers(KModifier.ABSTRACT)
//                    .returns(ClassName(packageName, key.simpleName)).build()
//            ).build()
//        ).build()
////        return FileSpec.builder(packageName, fileName)
////            .addType(TypeSpec.enumBuilder(enumName).apply {
////                    list.forEach {
////                        addEnumConstant(it.simpleName.uppercase())
////                    }
////                }.build()).addFunction(FunSpec.builder(fileName)
////                .addParameter("key", ClassName(packageName, enumName)).returns(key)
////                .beginControlFlow("return when (key)").apply {
////                    list.forEach {
////                        addStatement("${enumName}.${it.simpleName.uppercase()} -> %T()", it)
////                    }
////                }.endControlFlow().build()).build()
//    }
//
//    private fun getFactories(resolver: Resolver): Set<ClassName> {
//        return resolver.getSymbolsWithAnnotation(DaggerBaseView::class.qualifiedName.orEmpty())
//            .filterIsInstance<KSClassDeclaration>().filter(KSNode::validate)
//            .map { it.toClassName() }.toSet()
//    }
//
//    private fun getElements(
//        resolver: Resolver, factories: Set<ClassName>
//    ): Map<ClassName, List<ClassName>> {
//        val result = mutableMapOf<ClassName, MutableList<ClassName>>()
//        factories.forEach { result[it] = mutableListOf() }
//        resolver.getSymbolsWithAnnotation(InjectView::class.qualifiedName.orEmpty())
//            .filterIsInstance<KSClassDeclaration>().filter(KSNode::validate).forEach { d ->
//                d.superTypes.map {
//                    it.resolve().declaration.closestClassDeclaration()?.toClassName()
//                }.filter { result.containsKey(it) }.forEach { name ->
//                    result[name]?.add(d.toClassName())
//                }
//            }
//        return result
//    }
//
//}