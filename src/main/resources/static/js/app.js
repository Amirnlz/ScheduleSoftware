
const now = moment()
now.locale('fa')

const setDate = document.getElementById('current-date')
setDate.textContent = now.format('l')

const setTime = document.getElementById('current-time')
setTime.textContent = now.format('LT')

var picker = new Pikaday(
    {
        field: document.getElementById('birthDay'),
        firstDay: 1,
        minDate: new Date(),
        maxDate: new Date(2020, 12, 31),
        yearRange: [2000,2020]
    }
    );
    console.log("test")