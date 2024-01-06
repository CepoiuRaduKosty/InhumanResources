(() => {
    'use strict';

    document.addEventListener('DOMContentLoaded', function() {
        const forms = document.querySelectorAll('.needs-validation');


        Array.from(forms).forEach(form => {
            form.reset();


            const dateOfBirthInput = form.querySelector('#dateOfBirth');
            const hoursPerWeekInput = form.querySelector('#hoursPerWeek');
            const iBanInput = form.querySelector('#iBan');
            const monthlyBasicSalaryInput = form.querySelector('#monthlyBasicSalary');
            const bonusForSuccessInput = form.querySelector('#bonusForSuccess');
            const numberOfSharesInput = form.querySelector('#numberOfShares');
            const cumulatedSharesInput = form.querySelector('#cumulatedShares');
            const  salaryLevelSelect=form.querySelector('#salaryLevel');

            dateOfBirthInput.addEventListener('input', () => {
                validateDateOfBirth(dateOfBirthInput);
            });

            hoursPerWeekInput.addEventListener('input', () => {
                validateHoursPerWeek(hoursPerWeekInput);
            });

            iBanInput.addEventListener('input', () => {
                validateIBAN(iBanInput);
            });

            monthlyBasicSalaryInput.addEventListener('input', () => {
                validateMonthlyBasicSalary(monthlyBasicSalaryInput);
            });


            bonusForSuccessInput.addEventListener('input', () => {
                validateBonusForSuccess(bonusForSuccessInput);
            });

            numberOfSharesInput.addEventListener('input', () => {
                validateNumberOfShares(numberOfSharesInput);
            });

            cumulatedSharesInput.addEventListener('input', () => {
                validateCumulatedShares(cumulatedSharesInput);
            });
            salaryLevelSelect.addEventListener('change', () =>{
                validateBonusForSuccess(bonusForSuccessInput);
            });

            salaryLevelSelect.addEventListener('change', () =>{
               validateNumberOfShares(numberOfSharesInput);
            });

            salaryLevelSelect.addEventListener('change', () =>{
                validateCumulatedShares(cumulatedSharesInput);
            });

            form.addEventListener('submit', event => {
                const dateOfBirthValue = dateOfBirthInput.value;
                const hoursPerWeekValue = parseInt(hoursPerWeekInput.value, 10);
                const iBanValue = iBanInput.value;
                const monthlyBasicSalaryValue = parseFloat(monthlyBasicSalaryInput.value);
                const bonusForSuccessValue = parseFloat(bonusForSuccessInput.value);
                const numberOfSharesValue = parseInt(numberOfSharesInput.value, 10);
                const cumulatedSharesValue = parseInt(cumulatedSharesInput.value, 10);

                if (!dateOfBirthIsValid(dateOfBirthValue) ||
                    !hoursPerWeekIsValid(hoursPerWeekValue) ||
                    !IBANIsValid(iBanValue) ||
                    !monthlyBasicSalaryIsValid(monthlyBasicSalaryValue) ||
                    !bonusForSuccessIsValid(bonusForSuccessValue) ||
                    !numberOfSharesIsValid(numberOfSharesValue) ||
                    !cumulatedSharesIsValid(cumulatedSharesValue))
                {
                    event.preventDefault();
                    event.stopPropagation();
                }

                form.classList.add('was-validated');
            }, false);
        });
    });

    function dateOfBirthIsValid(value) {
        const currentDate = new Date();
        const selectedDate = new Date(value);

        return selectedDate <= currentDate;
    }

    function hoursPerWeekIsValid(value) {
        return value >= 10 && value <= 40 && !isNaN(value);
    }

    function IBANIsValid(iban) {
        iban = iban.replace(/ /g, '').toUpperCase();

        if (iban.length < 15 || iban.length > 34) {
            return false;
        }

        iban = iban.slice(4) + iban.slice(0, 4);

        iban = iban.replace(/[A-Z]/g, function (letter) {
            return letter.charCodeAt(0) - 55;
        });

        let remainder = iban;
        let block;

        while (remainder.length > 2) {
            block = remainder.slice(0, 9);
            remainder = parseInt(block, 10) % 97 + remainder.slice(block.length);
        }

        return parseInt(remainder, 10) % 97 === 1;
    }
    function monthlyBasicSalaryIsValid(value) {
        return value>0;
    }


    function bonusForSuccessIsValid(value) {
        const salaryLevelSelect = document.getElementById('salaryLevel');
        const bonusForSuccessInput = document.getElementById('bonusForSuccess');


        if (salaryLevelSelect && salaryLevelSelect.value === 'ASSOCIATE')
        {
            bonusForSuccessInput.setAttribute('placeholder', 'Bonus for Success');
            bonusForSuccessInput.removeAttribute('readonly');
            return value>0
        }
        if (salaryLevelSelect && salaryLevelSelect.value === 'EXECUTIVE')
        {
            bonusForSuccessInput.setAttribute('placeholder', 'Bonus for Success');
            bonusForSuccessInput.removeAttribute('readonly');
            return value>0
        }
        if (salaryLevelSelect && salaryLevelSelect.value === 'LECTURER')
        {
            bonusForSuccessInput.value = 0;
            bonusForSuccessInput.setAttribute('readonly', 'readonly');
            return true;
        }
        if (salaryLevelSelect && salaryLevelSelect.value === 'PROFESSOR')
        {
            bonusForSuccessInput.value = 0;
            bonusForSuccessInput.setAttribute('readonly', 'readonly');
            return true;
        }
    }

    function numberOfSharesIsValid(value) {
        const salaryLevelSelect = document.getElementById('salaryLevel');
        const numberOfShares = document.getElementById('numberOfShares');


        if (salaryLevelSelect && salaryLevelSelect.value === 'ASSOCIATE')
        {
            numberOfShares.value = 0;
            numberOfShares.setAttribute('readonly', 'readonly');
            return true;
        }
        if (salaryLevelSelect && salaryLevelSelect.value === 'EXECUTIVE')
        {
            numberOfShares.setAttribute('placeholder', 'Number Of Shares');
            numberOfShares.removeAttribute('readonly');
            return value>0
        }
        if (salaryLevelSelect && salaryLevelSelect.value === 'LECTURER')
        {
            numberOfShares.value = 0;
            numberOfShares.setAttribute('readonly', 'readonly');
            return true;
        }
        if (salaryLevelSelect && salaryLevelSelect.value === 'PROFESSOR')
        {
            numberOfShares.setAttribute('placeholder', 'Number Of Shares');
            numberOfShares.removeAttribute('readonly');
            return value>0
        }
    }

    function cumulatedSharesIsValid(value) {
        const salaryLevelSelect = document.getElementById('salaryLevel');
        const cumulatedShares = document.getElementById('cumulatedShares');


        if (salaryLevelSelect && salaryLevelSelect.value === 'ASSOCIATE')
        {
            cumulatedShares.value = 0;
            cumulatedShares.setAttribute('readonly', 'readonly');
            return true;
        }
        if (salaryLevelSelect && salaryLevelSelect.value === 'EXECUTIVE')
        {
            cumulatedShares.value = 0;
            cumulatedShares.setAttribute('readonly', 'readonly');
            return true;
        }
        if (salaryLevelSelect && salaryLevelSelect.value === 'LECTURER')
        {
            cumulatedShares.value = 0;
            cumulatedShares.setAttribute('readonly', 'readonly');
            return true;
        }
        if (salaryLevelSelect && salaryLevelSelect.value === 'PROFESSOR')
        {
            cumulatedShares.setAttribute('placeholder', 'Number Of Cumulated Shares');
            cumulatedShares.setAttribute('readonly', 'readonly');
            return true;
        }
    }


    function validateDateOfBirth(input) {
        const value = input.value;
        const isValid = dateOfBirthIsValid(value);

        if (!isValid) {
            input.setCustomValidity('Date of birth cannot be in the future');
        } else {
            input.setCustomValidity('');
        }
    }


    function validateHoursPerWeek(input) {
        const value = parseInt(input.value, 10);
        const isValid = hoursPerWeekIsValid(value);

        if (!isValid) {
            input.setCustomValidity('Hours per week must be between 10 and 40');
        } else {
            input.setCustomValidity('');
        }
    }

    function validateIBAN(input) {
        const iban = input.value;
        const isValid = IBANIsValid(iban);

        if (!isValid) {
            input.setCustomValidity('Invalid IBAN');
        } else {
            input.setCustomValidity('');
        }

        return isValid;
    }
    function validateMonthlyBasicSalary(input) {
        const value = parseFloat(input.value);
        const isValid = monthlyBasicSalaryIsValid(value);

        if (!isValid) {
            input.setCustomValidity('Monthly Basic Salary must be greater than 0');
        } else {
            input.setCustomValidity('');
        }
    }

    function validateBonusForSuccess(input) {
        const value = parseFloat(input.value);
        const isValid = bonusForSuccessIsValid(value);

        if (!isValid) {
            input.setCustomValidity('Bonus for Success must be 0 or greater');
        } else {
            input.setCustomValidity('');
        }
    }

    function validateNumberOfShares(input) {
        const value = parseInt(input.value, 10);
        const isValid = numberOfSharesIsValid(value);

        if (!isValid) {
            input.setCustomValidity('Number of Shares must be 0 or greater');
        } else {
            input.setCustomValidity('');
        }
    }

    function validateCumulatedShares(input) {
        const value = parseInt(input.value, 10);
        const isValid = cumulatedSharesIsValid(value);

        if (!isValid) {
            input.setCustomValidity('Cumulated Shares must be 0 or greater');
        } else {
            input.setCustomValidity('');
        }
    }
})();