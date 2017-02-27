(function () {
    document.querySelectorAll("[data-modal-show]").forEach(function (element) {
        var modalName = element.getAttribute("data-modal-show");

        element.addEventListener("click", function (event) {
            event.preventDefault();

            showModal(modalName);
        })
    });

    document.querySelectorAll("[data-modal-close]").forEach(function (element) {
        var modalName = element.getAttribute("data-modal-close");

        element.addEventListener("click", function (event) {
            event.preventDefault();

            closeModal(modalName);
        })
    });

    document.querySelector("form[data-get-in-touch]").addEventListener("submit", function (event) {
        event.preventDefault();

        var form = event.target;

        var contactInfo = {
            name: form.name.value,
            email: form.email.value,
            organization: form.organization.value,
            message: form.message.value,
            website: form.website.value
        };

        postJSON("/contact", contactInfo);

        form.reset();
        closeModal("get-in-touch");
    });

    function postJSON(location, contactInfo) {
        var headers = new Headers();
        headers.append("Content-Type", "application/json");

        fetch(location, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(contactInfo)
        });
    }

    function closeModal(modalName) {
        var modalElement = document.querySelector("[data-modal=\"" + modalName + "\"]");

        document.querySelector("body").classList.remove("no-scroll");
        modalElement.classList.remove("show")
    }

    function showModal(modalName) {
        var modalElement = document.querySelector("[data-modal=\"" + modalName + "\"]");

        document.querySelector("body").classList.add("no-scroll");
        modalElement.classList.add("show")
    }
})();
