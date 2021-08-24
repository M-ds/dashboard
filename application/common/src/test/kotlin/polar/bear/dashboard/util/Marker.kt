package polar.bear.dashboard.util

object Marker {
    val GIVEN: (description: (String?) -> Any) -> Any = {}
    val WHEN: (description: (String?) -> Any) -> Any = {}
    val THEN: (description: (String?) -> Any) -> Any = {}
}