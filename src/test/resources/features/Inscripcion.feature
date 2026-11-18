Feature: Inscripción a cursos
  Como estudiante de la institución
  Quiero inscribirme en los cursos disponibles
  Para llevarlos en el ciclo académico

  Background:
    Given el estudiante "I2024001" ya está inscrito en el curso 1

  Scenario: Inscripción correcta
    Given un estudiante nuevo con un código distinto en cada ejecución
    When se inscribe en el curso 2
    Then el sistema responde con código 200
    And la inscripción devuelta corresponde al estudiante y al curso 2
