document.querySelector('#r-amount').oninput = function() {
    // Grammar for regex.
    // value: START 
    //          ((ZERO|NONZERO)+
    //         | (ZERO|NONZERO)+ '.' (ZERO|NONZERO){0,2} ZERO*
    //         | '.' (ZERO|NONZERO){1,2} ZERO*) 
    //        END;
    // NONZERO: [1-9];
    // ZERO:     0;
    validCurrencyRegex = /^([0-9]+|[0-9]+\.[0-9]{0,2}0*|\.[0-9]{1,2}0*)$/;

    let requestVal = document.querySelector('#r-amount').value;
    
    if(validCurrencyRegex.test(requestVal)) {
        // valid
        document.querySelector('#submitBtn').disabled = false;
    } else {
        // invalid
        document.querySelector('#submitBtn').disabled = true;
    }
};

document.querySelector('#r-amount').oninput();