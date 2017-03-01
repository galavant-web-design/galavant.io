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

            closeModalName(modalName);
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

        postJSON("/contact", contactInfo).then(function () {
            doneModal("get-in-touch");
        });

        form.reset();
        waitModal("get-in-touch");
    });

    document.onkeydown = function (event) {
        event = event || window.event;
        if (event.keyCode == 27) {
            closeAllModals();
        }
    };

    function postJSON(location, contactInfo) {
        var headers = new Headers();
        headers.append("Content-Type", "application/json");

        return fetch(location, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(contactInfo)
        });
    }

    function waitModal(modalName) {
        findModal(modalName).classList.add("waiting");
    }

    function doneModal(modalName) {
        var modal = findModal(modalName);

        modal.classList.remove("waiting");
        modal.classList.add("done");
    }

    function closeAllModals() {
        document.querySelectorAll("[data-modal]").forEach(function (modal) {
            closeModal(modal);
        });
    }

    function closeModalName(modalName) {
        var modal = findModal(modalName);

        closeModal(modal);
    }

    function closeModal(modal) {
        modal.classList.add("fade");

        setTimeout(function () {
            document.querySelector("body").classList.remove("no-scroll");
            modal.classList.remove("fade");
            modal.classList.remove("show");
            modal.classList.remove("done");
        }, 250);
    }

    function showModal(modalName) {
        document.querySelector("body").classList.add("no-scroll");
        findModal(modalName).classList.add("show");
    }

    function findModal(modalName) {
        return document.querySelector("[data-modal=\"" + modalName + "\"]");
    }
})();
