package polar.bear.dashboard.utils

/**
 * This class is just a help tool to utilise while writing tests. This can help to create more structure when writing tests.
 */
object Marker {
    val GIVEN: (description: (String?) -> Any) -> Any = {}
    val WHEN: (description: (String?) -> Any) -> Any = {}
    val THEN: (description: (String?) -> Any) -> Any = {}
    val EXPECT: (description: (String?) -> Any) -> Any = {}
    val AND: (description: (String?) -> Any) -> Any = {}
}