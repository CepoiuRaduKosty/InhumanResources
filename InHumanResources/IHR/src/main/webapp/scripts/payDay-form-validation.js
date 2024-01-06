(() => {
    'use strict';

    const forms = document.querySelectorAll('.needs-validation');

    Array.from(forms).forEach(form => {
        form.reset();
        const dayOfMonthInput = form.querySelector('#payDayOfMonth');

        dayOfMonthInput.addEventListener('input', () => {
            validateDayOfMonth(dayOfMonthInput);
        });

        form.addEventListener('submit', event => {
            const dayOfMonthValue = parseInt(dayOfMonthInput.value, 10);

            if (!dayOfMonthIsValid(dayOfMonthValue)) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add('was-validated');
        }, false);
    });

    function dayOfMonthIsValid(value) {
        return value >= 1 && value <= 28;
    }

    function validateDayOfMonth(input) {
        const value = parseInt(input.value, 10);

        if (!dayOfMonthIsValid(value)) {
            input.setCustomValidity('Day of month must be between 0 and 28');
        } else {
            input.setCustomValidity('');
        }
    }
})();