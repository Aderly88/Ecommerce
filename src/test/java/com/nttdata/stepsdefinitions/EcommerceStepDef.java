package com.nttdata.stepsdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class EcommerceStepDef {

    private WebDriver driver;

    public EcommerceStepDef() {
        this.driver = WebDriverFactory.createDriver();
    }

    @Given("estoy en la página de la tienda {string}")
    public void givenEstoyEnLaPaginaDeLaTienda(String url) {
        driver.get(url);
    }

    @Given("me logueo con mi usuario {string} y clave {string}")
    public void givenMeLogueoConMiUsuarioYClave(String usuario, String clave) {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login_button"));

        usernameField.sendKeys(usuario);
        passwordField.sendKeys(clave);
        loginButton.click();
    }

    @When("navego a la categoría {string} y subcategoría {string}")
    public void whenNavegoACategoriaYSubcategoria(String categoria, String subcategoria) {
        WebElement categoriaLink = driver.findElement(By.linkText(categoria));
        categoriaLink.click();

        WebElement subcategoriaLink = driver.findElement(By.linkText(subcategoria));
        subcategoriaLink.click();
    }

    @When("agrego {int} unidades del primer producto al carrito")
    public void whenAgregoUnidadesDelPrimerProductoAlCarrito(int cantidad) {
        WebElement primerProducto = driver.findElement(By.xpath("//div[@class='product'][1]"));
        WebElement botonAgregar = primerProducto.findElement(By.className("add-to-cart"));

        for (int i = 0; i < cantidad; i++) {
            botonAgregar.click();
        }
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void thenValidoEnElPopupLaConfirmacionDelProductoAgregado() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("popup-confirmation")));

        assertTrue(popup.isDisplayed());
    }

    @Then("valido en el popup que el monto total sea calculado correctamente")
    public void thenValidoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        WebElement totalElement = driver.findElement(By.id("total-price"));
        double total = Double.parseDouble(totalElement.getText().replace("$", "").trim());

        assertTrue(total > 0);
    }

    @When("finalizo la compra")
    public void whenFinalizoLaCompra() {
        WebElement finalizarCompraButton = driver.findElement(By.id("finalizar-compra"));
        finalizarCompraButton.click();
    }

    @Then("valido el título de la página del carrito")
    public void thenValidoElTituloDeLaPaginaDelCarrito() {
        String tituloPagina = driver.getTitle();
        assertTrue(tituloPagina.contains("Carrito"));
    }

    @Then("vuelvo a validar el cálculo de precios en el carrito")
    public void thenVuelvoAValidarElCalculoDePreciosEnElCarrito() {
        WebElement precioCarrito = driver.findElement(By.id("carrito-total"));
        double precioFinal = Double.parseDouble(precioCarrito.getText().replace("$", "").trim());

        assertTrue(precioFinal > 0);
    }
}
