/** 
 * 1. USA
 * Define function getUSA()
 * Find the html element that contains "USA".
 * Print that element's contents. 
 */
function getUSA() {
    // find html element that contains USA
    let topElem;
    // topElem = document.documentElement; // doesn't work due to shadow-root element that browsers might insert
    topElem = document.querySelector('body'); // searching body as top-most tag to avoid shadow-root
    
    // find innermost tag containing 'USA' (case insensitive).
    // null returned if not found.
    // tag should not be null.
    let usaSearcher = {};
    usaSearcher.search = function(tag) {
        let usaRegex = /.*?[uU][sS][aA].*/; // Containing USA in general.
                                            // '.*?' -> match until next specified item
                                            // '[uU][sS][aA]' -> case-insensitive match of "usa"
                                            // '.*' -> match everything after, if any.
        let usaTag = null;
        
        // look for a match.
        let usaRegexMatchResult = usaRegex.exec(tag.outerHTML);
        if(usaRegexMatchResult == null) {
            // no match -> 'usa' not within tag
            usaTag = null;
        } else {
            // match found -> search children
            let tagChildNodes = tag.children; // only child tags.

            // Search until a match is found or all fail to match.
            for(let i = 0; usaTag == null && i < tagChildNodes.length; ++i) {
                usaTag = this.search(tagChildNodes[i]);
            }

            if(usaTag == null) { // match is within self & not child nodes.
                usaTag = tag;
            } // else: match is already returned by search.
        }

        return usaTag;
    };

    let usaContainingTag = usaSearcher.search(topElem)
    
    // Print the element's contents
    if(usaContainingTag != null) {
        console.log(usaContainingTag.innerHTML);
    } else {
        console.log('Could not find case-insensitive instance of USA')
    }

    return usaContainingTag;
}

/** 
 * 2. Sales
 * Define function getPeopleInSales()
 * Print the names of all the people in the sales department.
 */
function getPeopleInSales() {
    // Get all tags with employee names
    let empNames = document.querySelectorAll('.empName');
    let salesRegex = /\s*[sS][aA][lL][eE][sS]\s*/;
    let salesPpl = [];
    empNames.forEach(td => {
        // get row of 2 cells: [name],[department]
        let row = td.parentElement;
        let department = row.children[1].innerText;
        if(salesRegex.exec(department) != null) {
            salesPpl.push(row.children[0].innerText);
        }
    });

    salesPpl.forEach(p => console.log(p));

    return salesPpl;
}

/** 
 * 3. Click Here
 *  Define function getAnchorChildren()
 * Find all anchor elements with a <span> child.
 * Print the contents of <span>
 */
function getAnchorChildren() {
    let anchoredSpans = document.querySelectorAll('a span');
    if(anchoredSpans != null) {
        anchoredSpans.forEach(tag => console.log(tag.innerText));
    }

    return anchoredSpans;
}

/** 
 * 4. Hobbies
 * Define function getSkills()
 * Find all checked options in the 'skills' select element.
 * Print the value and the contents.
 */
function getSkills() {
    
    let checkedSkills = null;
    // Gets current selection.
    checkedSkills = document.querySelectorAll('select[name="skills"] option:checked');
    if(checkedSkills != null) {
        checkedSkills.forEach(opt => console.log(opt.innerText));
    }


    // Alternative: Gets all with selected="selected" attribute
    // checkedSkills = document.querySelectorAll('select[name="skills"] option');
    // let selSkills = [];

    // if(checkedSkills != null) {
    //     checkedSkills.forEach(tag => {
    //         let selectedAttr = tag.getAttribute('selected');
    //         if(selectedAttr != null && selectedAttr == 'selected') {
    //             selSkills.push(tag);
    //             console.log(tag.innerText);
    //         }
    //     });
    // }
    // checkedSkills = selSkills;


    return checkedSkills;
}

/** 
 * 5. Custom Attribute
 * Define function getCustomAttribute()
 * Find all elements with "data-customAttr" attribute
 * Print the value of the attribute.
 * Print the element that has the attribute.
 */
function getCustomAttribute() {
    let dataCustAttrElems = document.querySelectorAll('*[data-customAttr]');
    
    if(dataCustAttrElems != null) {
        dataCustAttrElems.forEach(tag => {
            let attrVal = tag.getAttribute('data-customAttr');
            let tagStr = tag.outerHTML;
            console.log(tag.nodeName + ': ');
            console.log('    attribute value: ' + attrVal);
            console.log('    element: ' + tagStr);
        });
    }

    return dataCustAttrElems;
}

/** 
 * 6. Sum Event
 * NOTE: Write unobtrusive Javascript
 * Regarding these elements:
 * <input id="num1" class="nums" type="text"/>
 * <input id="num2" class="nums" type="text"/>  
 * <h3>Sum: span id="sum"></span></h3>
 * Define onchange event handler.
 * Add <input> element values.
 * Put the sum in the <span> element.
 * If values cannot be added, put "Cannot add" in the <span> element
 */
let sumHandler = function() {
    let val1 = document.querySelector('#num1').value;
    let val2 = document.querySelector('#num2').value;
    let outputTag = document.querySelector('#sum');

    val1 = parseFloat(val1);
    val2 = parseFloat(val2);

    if(isNaN(val1) || isNaN(val2)) {
        outputTag.innerText = '';
    } else {
        outputTag.innerText = (val1 + val2);
    }
}
document.querySelector('#num1').onchange = sumHandler;
document.querySelector('#num2').onchange = sumHandler;

/** 
 * 7. Skills Event
 * NOTE: Write unobtrusive Javascript
 * When user selects a skill, create an alert with a message similar to:
 * "Are you sure CSS is one of your skills?"
 * NOTE: no alert should appear when user deselects a skill.
 */
document.querySelector('select[name="skills"]').onchange = function() {
    let selectedVal = document.querySelector('select[name="skills"] option:checked');
    alert('Are you sure ' + selectedVal.innerText + ' is one of your skills?');
};

/** 
 * 8. Favorite Color Event
 * NOTE: Write unobtrusive Javascript
 * NOTE: This is regarding the favoriteColor radio buttons.
 * When a user selects a color, create an alert with a message similar to:
 * "So you like green more than blue now?"
 * In this example, green is the new value and blue is the old value.
 * Make the background color (of all favoriteColor radio buttons)
 * the newly selected favoriteColor
 */
let colorAlerter = function() {
    let prevSelection = document.querySelector('input[name="favoriteColor"]:checked');
    
    let alerter = function() {
        let nextSelection = document.querySelector('input[name="favoriteColor"]:checked');
        
        // Leave if not the new selection.
        if(nextSelection != this) {
            return;
        }

        // Create alert message with new color & old color
        if(prevSelection != null) {
            alert('So you like ' + nextSelection.value + ' more than ' + prevSelection.value + ' now?');
        }

        prevSelection = nextSelection;

        // Set style of radio buttons: bgcolor -> new color
        let helloWorldStyle = document.querySelector('#favColorStyle');
        if(styleTag == null) {
            // style tag not present -> create one & insert it at end of head tag
            styleTag = document.createElement('style');
            styleTag.setAttribute('type', 'text/css');
            styleTag.setAttribute('id', 'favColorStyle');

            let headTag = document.querySelector('head');
            headTag.children[headTag.children.length - 1].insertAdjacentElement('afterEnd', styleTag);
        }

        styleTag.innerText = '\n.favColor { \n'
            + 'background-color: ' + nextSelection.value + ';\n'
            + '}\n';
    };

    return alerter;
}();
document.querySelectorAll('input[name="favoriteColor"]').forEach(tag => {
    // Create a span around the input element; 
    // radio button input themselves don't have a background
    let wrapperSpan = document.createElement('span');
    wrapperSpan.setAttribute('class', 'favColor');
    tag.parentElement.insertBefore(wrapperSpan, tag);
    wrapperSpan.appendChild(tag);

    tag.onchange = colorAlerter;
});


/** 
 * 9. Show/Hide Event
 * NOTE: Write unobtrusive Javascript
 * When user hovers over an employees name:
 * Hide the name if shown.
 * Show the name if hidden.
 */
document.querySelectorAll('.empName').forEach(tag => {
    tag.onmouseover = function() {
        let currentHover = document.querySelector('.empName:hover');
        
        if(this == currentHover) {
            document.querySelectorAll('.empName').forEach(tag => {
                tag.removeAttribute('hidden');
            });

            this.setAttribute('hidden', true);
        }
    };
});


/** 
 * 10. Current Time
 * Regarding this element:
 * <h5 id="currentTime"></h5>
 * Show the current time in this element in this format: 9:05:23 AM
 * The time should be accurate to the second without having to reload the page.
 */
setInterval(() => {
    // Get current time.
    let now = new Date();
    let hrs = now.getHours();
    let hr12suffix = hrs < 12 ? "AM" : "PM";
    hrs = hrs % 12;
    hrs = hrs == 0 ? 12 : hrs;
    let min = now.getMinutes();
    let sec = now.getSeconds();

    // create formatted string
    let timeStr = hrs + ':' + min + ':' + sec + ' ' + hr12suffix;
    
    // Set tag to time
    document.querySelector('#currentTime').innerText = timeStr;
}, 500);


/** 
 * 11. Delay
 * Regarding this element:
 * <p id="helloWorld">Hello, World!</p>
 * Three seconds after a user clicks on this element, change the text to a random color.
 */
document.querySelector('#helloWorld').onclick = () => {
    setTimeout(() => {
        // Create random color.
        let colorStr = Math.trunc(Math.random()*0x1000000).toString(16);
        if(colorStr.length < 6) {
            let filler = '0'.repeat(6-colorStr.length);
            colorStr = filler + colorStr;
        }
        colorStr = '#' + colorStr;

        // Create internal stylesheet if non-existent
        let helloWorldStyle = document.querySelector('#helloWorldStyle');
        if(helloWorldStyle == null) {
            helloWorldStyle = document.createElement('style');
            helloWorldStyle.setAttribute('id', 'helloWorldStyle');
            helloWorldStyle.setAttribute('type', 'text/css');

            document.querySelector('head').appendChild(helloWorldStyle);
        }

        // Set style
        helloWorldStyle.innerText = '#helloWorld {\n' 
            + 'color: ' + colorStr + ';\n'
            + '}';
    }, 3000);
};


/** 
 * 12. Walk the DOM
 * Define function walkTheDOM(node, func)
 * This function should traverse every node in the DOM.
 * Use recursion.
 * On each node, call func(node).
 */
function walkTheDOM(node, func) {
    // Use func on node.
    func(node);
    
    // Get child nodes.
    let nodeChildren;

    // Uncomment the line with the correct assumption {
    // Assumption: nodes are tags
    nodeChildren = node.children;
    
    // Assumption: nodes are anything considered a node including TextNodes, etc.
    // nodeChildren = node.childNodes;
    // }

    // 
    for(let i = 0; i < nodeChildren.length; ++i) {
        walkTheDOM(nodeChildren[i], func);
    }
}