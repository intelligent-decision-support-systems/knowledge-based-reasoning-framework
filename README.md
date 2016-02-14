# KRF
Knowledge-based Reasoning and Recommendation Framework (KRF)

<br>
<img src="https://github.com/intelligent-decision-support-systems/knowledge-based-reasoning-framework/blob/gh-pages/images/krf_crop.jpg">
<br>
**For single inputCase (fact) it will show recommendation in GUI**
<br>
<img src="https://github.com/intelligent-decision-support-systems/knowledge-based-reasoning-framework/blob/gh-pages/images/recommendation.jpg">
<br>
## How to Build & Run

### Prerequisites
Download and install maven [- download](https://maven.apache.org/download.cgi)

### Clone the repository
Run the following command<br>
`git clone https://github.com/intelligent-decision-support-systems/knowledge-based-reasoning-framework.git`
<br>OR<br>
Download zip file [- download](https://github.com/intelligent-decision-support-systems/knowledge-based-reasoning-framework/archive/master.zip)

### Build
* Enter the following command from the root directory of the repsository<br>
  `mvn clean install`

### Run
* Run `run_krf.sh` linux/mac
* Run `run_krf.bat` windows
<br>
**Note:** <br>
* The recommendation results will store in the root directory in `recommendationResults.csv` file.
* You can append inputCases e.g. facts and rules from the GUI
* You can play with KRF by adding/removing inputCases e.g. facts from `krf_input_cases_single.json` file
* For single inputCase the KRF will show the conclusion in GUI
### License
The code is licensed using the [Apache License Version 2.0](http://www.apache.org/licenses/LICENSE-2.0)


[Roadmap](https://github.com/intelligent-decision-support-systems/knowledge-based-reasoning-framework/wiki/Roadmap)
