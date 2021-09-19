# IPOscore: Clinical prognosis and risk prediction of postoperative complications in cancer patients

In the context of our work, a clinical decision support system with graphical facilities was produced, aimed for the oncological surgery domain.

## Installation:

Python 3+

All dependencies defined in **requirements.txt**. You can install them by:

```
$ conda config --append channels conda-forge
$ conda create --name <env> --file requirements.txt
$ conda activate <env>
```

## Usage:

After installing the required dependencies and activating your freshly created environment you should be able to run our app. 

You can see our interface for the clinical decision support system by running and accessing http://127.0.0.1:8050/:

```
$ python main.py
```

**Note**: The webapp is also available for online use, hosted by Heroku at https://iposcore.herokuapp.com/


---

**Disclaimer**: We advise caution for clinicians who intend to use this tool as a predictive guide. Clinicians must balance the predictions from this tool against their practical experience. Authors cannot guarantee the accuracy of the calculation for any particular patient. The risk models were trained with patients' data from IPO-Porto.

---

The contributions are currently under review. Please, contact Rafael Costa (rafael.s.costa@tecnico.ulisboa.pt), Rui Henriques (rmch@tecnico.ulisboa.pt), or Daniel Gonçalves (dmateusgoncalves@tecnico.ulisboa.pt) to obtain the updated reference.

Guidelines to access data may be available upon request.

---

This beta web application version has been developed by Daniel Gonçalves, Lúcio Santos, Rui Henriques, Rafael Costa. It is part of the IPOscore research project.
