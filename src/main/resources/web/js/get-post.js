const getData = async (url) => {

    const response = await fetch(url);

    if (!response.ok) {
        throw new Error(`Error ${url}, status ${response.status}`);
    }

    return await response.json();

};

const sendData = async (url, data) => {
    const response = (await fetch(url, {
        method: 'POST',
        headers: new Headers({'content-type': 'application/json'}),
        body: data,
    }));

    return await response.json();
}

export {getData, sendData}