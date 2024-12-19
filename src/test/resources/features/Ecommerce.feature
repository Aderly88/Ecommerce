@test
Feature: Automatización de Pruebas de Regresión para Tienda en Línea

  Scenario: Validación del precio de un producto en la tienda

    Given estoy en la página de la tienda "https://qalab.bensg.com/store"
    And me logueo con mi usuario "adderlyxsiempre@gmail" y clave "Exito2025#"
    When navego a la categoría "Clothes" y subcategoría "Men"
    And agrego 2 unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el título de la página del carrito
    And vuelvo a validar el cálculo de precios en el carrito

  Scenario: Inicio de sesión con credenciales inválidas

    Given estoy en la página de la tienda "https://qalab.bensg.com/store"
    When me logueo con mi usuario "Aderly" y clave "872837jd"
    Then verifico que aparece un mensaje de error de autenticación

  Scenario: Navegación a una categoría inexistente

    Given estoy en la página de la tienda "https://qalab.bensg.com/store"
    And me logueo con mi usuario "adderlyxsiempre@gmail" y clave "Exito2025#"
    When navego a la categoría "Autos"
    Then verifico que aparece un mensaje indicando "Categoría no encontrada"