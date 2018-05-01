(ns status-im.ui.screens.extensions.db
  (:require-macros [status-im.utils.db :refer [allowed-keys]])
  (:require [cljs.spec.alpha :as spec]))

(spec/def :extension/extension-id (spec/nilable string?))
(spec/def :extension/name (spec/nilable string?))

(spec/def :extension/extension
  (allowed-keys
    :req-un [:extension/extension-id
             :extension/name]))

(spec/def :extension/extensions (spec/nilable (spec/map-of :global/not-empty-string :extension/extension)))
