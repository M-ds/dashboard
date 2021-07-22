package polar.bear.dashboard.util.text

object Marker {
    val GIVEN: (description: (String?) -> Any) -> Any = {}
    val WHEN: (description: (String?) -> Any) -> Any = {}
    val THEN: (description: (String?) -> Any) -> Any = {}
}