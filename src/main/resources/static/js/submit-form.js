

document.addEventListener('DOMContentLoaded',()=> {
    document.getElementById('submitItem')
        .addEventListener('submit', async (e) =>{
            const result = await processItemFormData(e);
            console.log("API result:", result);
            if (!result.error && result.id) {
                //const encodedId = encodeURIComponent(result.id);
                window.location.href = `/item-success/${result.id}`;
            } else {
                alert("Error: " + result.error);
            }
        });
});


async function processItemFormData(e) {
    e.preventDefault();

    const myForm = e.target;
    const formData = new FormData(myForm);
    const jsonObj = JSON.stringify(Object.fromEntries(formData));

    try {
        const response = await fetch('/api/addItem', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: jsonObj
        });

        if (!response.ok) {
            const errorText = await response.json();
            throw new Error(`API error (${response.status}): ${errorText}`);
        }

        return await response.json();

    } catch (err) {
        console.error("Error during API request:", err);
        return { error: err.message };
    }
}
