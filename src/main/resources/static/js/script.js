$("#minusBtn").attr("disabled", true);
$("#plusBtn").click(function () {
    let quantity = parseInt($("#quantity").text());
    quantity = quantity + 1;
    $("#quantity").text(quantity);
    $("#minusBtn").attr("disabled", false);
});
$("#minusBtn").click(function () {
    let quantity = parseInt($("#quantity").text());
    quantity = quantity - 1;
    $("#quantity").text(quantity);
    if (quantity === 1) {
        $(this).attr("disabled", true);
    }
});

$("#grade").change(function () {
    reloadProducts();
});

$("#price").change(function () {
    reloadProducts();
})

$("#search").on("input", function () {
   reloadProducts();
});

function reloadProducts() {
    let grade = $("#grade").val();
    let price = $("#price").val();
    let name = $("#search").val();
    fetch(`/api/product/filter?grade=${grade}&price=${price}&name=${name}`)
        .then(res => res.json())
        .then(data => {
            $(".product-container").empty();
            for (const product of data) {
                $(".product-container").append(`
                    <div class="col-3 mb-4">
                        <a href="/${product.id}" class="card d-flex flex-column h-100 align-items-stretch">
                            <div class="img-wrapper card-img-top img-fluid">
                                <img src="/public/productImages/${product.boxImage}" class="first-image" alt="Product Image">
                                <img src="/public/productImages/${product.frontImage}" class="second-image" alt="Product Image">
                            </div>
                            <div class="card-body flex-fill">
                                <h5 class="card-title truncate-text">${product.name}</h5>
                                <p class="card-text text-danger"><span class="fw-bold">${product.price.toLocaleString('en-US')}</span> <i class="fa-solid fa-dong-sign"></i></p>
                            </div>
                        </a>
                    </div>
                `);
            }
        })
        .catch(err => {
            console.log(err);
        })
}
